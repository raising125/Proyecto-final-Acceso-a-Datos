package literals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import entitatsHib.literal;
import entitatsHib.log;

public class literalHib {
	private static Session _session;
	private static ArrayList<String> logs = new ArrayList<String>();
	private static ArrayList<Date> fechas = new ArrayList<Date>();
	
	public literalHib(Session reciveSession){
		this._session =reciveSession;
	}
	
	//metodo cargarDesdeBD: carga el texto de los errores y demas desde la base de datos.
	//param: el lit_clau de la tabla literal
	public static String cargarDesdeBD(String idioma, String buscar){
		List<literal> listEle = (List<literal>) _session.createQuery(" FROM Literal WHERE idi_cod= '"+idioma+"' AND lit_clau='"+buscar+"'").list();
		String respuesta="";
		respuesta=listEle.get(0).get_error();
		return respuesta;
	}
		
	//metodo limpiarLog: borra el contenido de la tabla log de la base de datos
	public static void limpiarLog(){
		_session.beginTransaction();
		_session.createSQLQuery("TRUNCATE TABLE LOG").executeUpdate();
		_session.getTransaction().commit();
		_session.clear();
		System.out.println(">> Historial borrado...");
	}
	
	//metodo logear: comprueba si login esta activado y guarda en el historial, cada 10 logs envia a la base de datos
	//param: texto a guardar en el historial
	public static void logear(Boolean login, String log){
		try{
			if(login==true){
				if(logs.size()<10 && fechas.size()<10){
					logs.add(log);
					Date fecha = new Date();
					fechas.add(fecha);
				}else{
					for(int i=0;i<logs.size();i++){
						log logii = new log();
						logii.set_texto(logs.get(i));
						logii.set_fecha(fechas.get(i));
						_session.beginTransaction();
						_session.save(logii);
						_session.getTransaction().commit();
						_session.clear();
					}
					System.out.println("------------------------");
					System.out.println("Log actualizado");
					logs = new ArrayList<String>();
					fechas = new ArrayList<Date>();
					logs.add(log);
					Date fecha = new Date();
					fechas.add(fecha);
				}
			}
		}catch(Exception e){
			System.out.println("------------------------");
			System.out.println("Error al logear!");
			e.printStackTrace();
		}
	}
	
	//Deprecated: log: si el log esta activado este metodo guarda la informacion en el historial
	public static void log(Boolean login, String text){
		try{
			if(login==true){
				Path path= Paths.get("C:\\Users\\daniel\\workspace\\navegador\\log.txt");
				Files.write(path, Arrays.asList(text), StandardCharsets.UTF_8, 
				Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
}
