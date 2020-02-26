package logs;

import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;

import entitatsHib.log;
import literals.literalHib;

public class logHib extends logs.log{
	private ArrayList<log> logs;
	private log l;
	private Session session;
	private short counter;
	private short last;

	public logHib() {
		this.session = literalHib.getSessionFactory().openSession();
		this.counter = 0;
		this.logs = new ArrayList<log>();
	}

	// Afegir element a logs
	public void log(String s) {
		l = new log();
		l.set_fecha(new Date());
		l.set_texto(s);
		logs.add(l);
		if (++counter > 2) {
			savelog();
			counter = 0;
		}
	}

	// Enviar tots els logs a la bbdd
	public void savelog() {
		short len = (short) logs.size();
		for (short i = last; i < len; ++i) {
			session.beginTransaction();
			session.save(logs.get(i));
			session.getTransaction().commit();
			session.clear();
		}
		last = len;
	}
	
	public void simplelog(String s) {
		l = new log();
		l.set_fecha(new Date());
		l.set_texto(s);
		session.beginTransaction();
		session.save(l);
		session.getTransaction().commit();	
	}

	// Buidar LOG table
	public void clean() {
		try {
			session.beginTransaction();
			session.createSQLQuery("TRUNCATE TABLE LOG").executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERROR: Couldn't truncate");
			System.out.println(e.getMessage());
		}
	}
	
}
