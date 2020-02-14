package configuracio;

import java.io.File;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entitatsHib.literal;
import entitatsHib.configuracio;
import entitatsHib.idioma;
import entitatsHib.log;
import entitatsHib.usuari;

public class configuracioHib {
	private static SessionFactory sessionFactory;
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

}
