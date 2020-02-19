package literals;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entitatsHib.configuracio;
import entitatsHib.idioma;
import entitatsHib.literal;
import entitatsHib.log;
import entitatsHib.usuari;

public class literalHib extends literal{
	private Session _session;
	private static SessionFactory sessionFactory;
	
	public literalHib(){
		this._session =literalHib.getSessionFactory().openSession();
	}
	
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
        		Configuration configuration = new Configuration().configure(new File("hibernate.cfg.xml"));		//se crea la config para hibernate desde xml
        		configuration.addAnnotatedClass(configuracio.class); 
        		configuration.addAnnotatedClass(idioma.class); 
        		configuration.addAnnotatedClass(literal.class);
        		configuration.addAnnotatedClass(log.class); 
        		configuration.addAnnotatedClass(usuari.class); 
        		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
	
	//metodo cargarDesdeBD: carga el texto de los errores y demas desde la base de datos.
	//param: el lit_clau de la tabla literal
	@SuppressWarnings("unchecked")
	public String get_literal(String idi, String code){
		List<literal> list = (List<literal>) _session.createQuery(" FROM Literal WHERE idi_cod= '"+idi+"' AND lit_clau='"+code+"'").list();
		if (list!=null && list.size()> 0) {
			String respuesta = list.get(0).get_error();
			return respuesta;
		}else {
			return null;
		}
	}
	
	public void close() {
		_session.close();
	}
}
