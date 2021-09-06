import Model.Client;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import Model.*;

public class Main {

    public static void main(String[] args) throws Exception {

        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//            Model.Klient k = new Model.Klient("cezary", "boguszewski", LocalDate.of(1997, 10, 23), 990, "xdxd", 20);
//            System.out.println(k.getWiek());
//
//            Dane d = new Dane();
//            d.setMarka("POlo");
//            d.setRokProdukcji(1997);
//
//            Pojazd p = new Pojazd();
//            Pojazd p2 = new Pojazd();
//            p.setIdPojazd(2);
//            p.setIdPojazd(3);
//            p.setCena(20);
//            p.setDane(d);
//            System.out.println(p);

            Vehicle p1 = new MotorCycle();



            Session session = sessionFactory.openSession();
            session.beginTransaction();
//            session.save(klient1);
//            session.save(klient2);

            session.getTransaction().commit();
            session.close();

            System.out.println("\nMovies and actors from the db:");


            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Client> moviesFromDb = session.createQuery( "from Client " ).list();
            for ( Client klient : moviesFromDb) {
                System.out.println(klient);
            }
//            List<Dane> actorsFromDb = session.createQuery( "from dane " ).list();
//            for ( Dane actor : actorsFromDb) {
//                System.out.println(actor);
//
//            }
            session.getTransaction().commit();
            session.close();

        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually
            StandardServiceRegistryBuilder.destroy( registry );
        }
        finally {
            if (sessionFactory != null) {
                sessionFactory.close();
                sessionFactory = null;
            }
        }
    }
}

