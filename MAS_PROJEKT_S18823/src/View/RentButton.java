package View;

import Model.Client;
import Model.Vehicle;
import Model.CarRental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

class RentButton extends DefaultCellEditor
{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private Vehicle goodVehicle;
    private String pojazdString;
    private Session session;


    public RentButton(JTextField txt, Client client, SessionFactory sessionFactory, CarRental carRental) {
        super(txt);

        btn=new JButton();
        btn.setText("wypozycz");
        btn.setOpaque(false);



        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //comparing data from button to data from Db and realize renting service
                session = sessionFactory.openSession();
                session.beginTransaction();

                List<Vehicle> vehicleFromDb = session.createQuery("from Vehicle ").list();
                System.out.println(lbl);
                for ( Vehicle vehicle : vehicleFromDb) {

                    if(Integer.parseInt(lbl)== vehicle.getIdVehicle()){
                        System.out.println("ok");
                        goodVehicle = vehicle;
                    }
                }


                session.getTransaction().commit();
                session.close();


                if(goodVehicle.isIsAvailable()==false){
                    showMessageDialog(null, "Pojazd niedostepny");
                }else{
                    RentFrame formFrame = new RentFrame("RentWindow",sessionFactory,client, goodVehicle, carRental);
                }




            }
        });


    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj,
                                                 boolean selected, int row, int col) {

        //Set tex to the button and get data from this

        lbl=(obj==null) ? "":obj.toString();

        pojazdString = obj.toString();
        clicked=true;
        btn.setText("wypozycz");

        return btn;
    }



    @Override
    public boolean stopCellEditing() {


        clicked=false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {

        super.fireEditingStopped();
    }
}