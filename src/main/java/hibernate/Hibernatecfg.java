package hibernate;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import model.Borrower;
import model.location;
import model.membership;
import model.MembershipType;
import model.room;
import model.Shelf;
import model.User;
import model.Book;

public class Hibernatecfg {

	public static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		
		if(sessionFactory == null) {
			try {
				Configuration conf = new Configuration();
				
				Properties settings = new Properties();

				settings.setProperty(Environment.DRIVER, "org.postgresql.Driver");
				settings.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/webtesting");
				settings.setProperty(Environment.USER, "postgres");
				settings.setProperty(Environment.PASS, "Hategekimana2%");
				settings.setProperty(Environment.HBM2DDL_AUTO, "update");
				settings.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
				settings.setProperty(Environment.SHOW_SQL, "true");

				conf.setProperties(settings);
				conf.addAnnotatedClass(Book.class);
				conf.addAnnotatedClass(Borrower.class);
				conf.addAnnotatedClass(location.class);
				conf.addAnnotatedClass(MembershipType.class);
				conf.addAnnotatedClass(membership.class);
				conf.addAnnotatedClass(room.class);
				conf.addAnnotatedClass(Shelf.class);
				conf.addAnnotatedClass(User.class);
				sessionFactory = conf.buildSessionFactory();
			}catch(Exception ex) {
				System.out.println("Error Message is : "+ex.getMessage());
			}

			return sessionFactory;
		}else {
			return sessionFactory;
		}
	}
}