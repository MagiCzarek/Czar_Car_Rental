import Model.*;
import View.ClientFrame;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
/**

 Cezary Boguszewski s18823 MAS PRO IMPLEMENTACJA


 */
public class Client {

    StandardServiceRegistry registry;
    SessionFactory sessionFactory;
    boolean save;
    ClientFrame clientFrame;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try {

            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();




            //ADDING DEMO DATA


            Model.Client k = new Model.Client();
            k.setName("Cezary");
            k.setSecondName("Boguszewski");
            k.setIdClient(1);

            Worker prac = new Worker(20,5,3);
            Worker prac2 = new Worker(40,5,3);
            Worker.calculateMonthSalary();
            System.out.println(prac.getMonthSalary());
            System.out.println(prac2.getMonthSalary());



            Vehicle p1 = new MotorCycle();
            Vehicle p2 = new Car();
            Vehicle p3 = new Car();
            Vehicle p4 = new Car();
            Vehicle p5 = new Car();
            Vehicle p6 = new Car();
            p1.setIdVehicle(1);
            p2.setIdVehicle(2);
            p3.setIdVehicle(3);
            p4.setIdVehicle(3);
            p5.setIdVehicle(4);
            p6.setIdVehicle(5);

            p1.setIsAvailable(true);
            p2.setIsAvailable(true);
            p3.setIsAvailable(false);
            p4.setIsAvailable(false);
            p5.setIsAvailable(false);
            p6.setIsAvailable(false);
            p1.setPrice(20);
            p2.setPrice(30);
            p3.setPrice(40);
            p4.setPrice(40);
            p5.setPrice(1000);
            p6.setPrice(2000);



            Data d1 = new Data();
            d1.setBrand("Volvo");
            d1.setModel("XC60");
            d1.addVehicle(p1);
            d1.addVehicle(p2);
            d1.addVehicle(p3);


            CarRental carRental = new CarRental();
            carRental.addVehicle(p1);
            carRental.addVehicle(p2);
            carRental.addVehicle(p3);
            carRental.addVehicle(p4);
            carRental.addVehicle(p5);
            carRental.addVehicle(p6);

            Session session;
            Session session1;

            session1 = sessionFactory.openSession();
            session1.beginTransaction();

            session1.save(k);


            session1.save(p1);
            session1.save(p2);
            session1.save(p3);
            session1.save(p4);
            session1.save(p5);
            session1.save(p6);
            session1.save(d1);


            session1.save(carRental);

            session1.getTransaction().commit();
            session1.close();


            // CREATING CLIENT WINDOW
            clientFrame = new ClientFrame("ClientWindow", k, sessionFactory, carRental); // construct a MyFrame object
            clientFrame.setVisible(true);




            System.out.println("\nKlienci z bazy:");

            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Model.Client> clientsFromDb = session.createQuery( "from Client " ).list();
            for ( Model.Client klient : clientsFromDb) {
                System.out.println(klient);
            }

            session.getTransaction().commit();
            session.close();



        } catch (Exception e) {
            e.printStackTrace();

            StandardServiceRegistryBuilder.destroy(registry);
        } finally {
            // destroying session if window closed
            if (sessionFactory != null && !clientFrame.isActive()) {
                sessionFactory.close();
                sessionFactory = null;
            }
        }
    }

}
