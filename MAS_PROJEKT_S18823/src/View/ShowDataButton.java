package View;

import Model.Client;
import Model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

class ShowDataButton extends DefaultCellEditor
{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private Vehicle goodVehicle;
    private String pojazdString;
    private Session session;

    public ShowDataButton(JTextField txt, Client client, SessionFactory sessionFactory) {
        super(txt);

        btn=new JButton();
        btn.setText("dane");

        btn.setSize(20,20);


        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                session = sessionFactory.openSession();
                session.beginTransaction();
                //comparing data from button to data from DB and showing data
                List<Vehicle> vehicleFromDb = session.createQuery("from Vehicle ").list();
                System.out.println(lbl);
                for ( Vehicle vehicle : vehicleFromDb) {

                    if(Integer.parseInt(lbl)== vehicle.getIdVehicle()){
                        System.out.println("git");
                        goodVehicle = vehicle;
                    }

                }
                session.getTransaction().commit();
                session.close();
                System.out.println(goodVehicle.getData());
                JTextField jTextField = new JTextField("Dane Pojazdu");
                showMessageDialog(jTextField, "Dane Pojazdu \n"+ goodVehicle.getData());

            }
        });
    }

    //OVERRIDE A COUPLE OF METHODS
    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj,
                                                 boolean selected, int row, int col) {

        //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
        lbl=(obj==null) ? "dane":obj.toString();
        btn.setText("dane");
        clicked=true;
        return btn;
    }


    @Override
    public boolean stopCellEditing() {

        //SET CLICKED TO FALSE FIRST
        clicked=false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {

        super.fireEditingStopped();
    }
}