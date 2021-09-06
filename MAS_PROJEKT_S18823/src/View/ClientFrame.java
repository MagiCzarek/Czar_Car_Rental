package View;

import Model.Client;
import Model.Vehicle;
import Model.DrivingLicense;
import Model.CarRental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import static javax.swing.JOptionPane.showMessageDialog;

public class ClientFrame extends JFrame {
    private static final int WINDOW_X_SIZE = 515;
    private static final int WINDOW_Y_SIZE = 900;
    private static final Pattern idDrivingLicensePattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static final Pattern nameAndSecondNAmePattern = Pattern.compile("^[a-zA-Z\\s]+");
    JPanel panel;
    JLabel label;
    private MyButton drivingLicenseButton;
    private MyButton vehicleListButton;

    private JLabel logoLabel;
    private ImageIcon image1;

    private JTextPane namePane;
    private JTextPane secondNamePane;
    private JTextPane idDrivingLPane;
    private JTextPane categoryPane;
    private MyButton addDrivingLButton;
    private JTable vehicleTable;
    private JScrollPane vehiclePanel;
    private Client klient;
    private SessionFactory sessionFactory;
    private CarRental carRental;
    private JPanel logoPanel;

    //Main class for clien Service




    public ClientFrame(String title, Client client, SessionFactory sessionFactory, CarRental carRental) throws Exception{
        super(title);// invoke the JFrame constructor
        this.klient= client;
        this.sessionFactory=sessionFactory;
        this.carRental = carRental;

        //adding to frame Components

        vehiclePanel = new JScrollPane();
        vehiclePanel.setBounds(70, 350, 350, 200);
        vehiclePanel.setVisible(false);
        this.add(vehiclePanel);




        drivingLicenseButton = new MyButton("Prawo Jazdy");
        drivingLicenseButton.setBounds(250, 0, 125, 30);

        this.add(drivingLicenseButton);

        namePane = new JTextPane();

        namePane.setText("Imie");
        namePane.setBounds((WINDOW_X_SIZE / 2) - 85, 250, 145, 70);
        namePane.setBorder(BorderFactory.createLineBorder(Color.black));
        namePane.setBackground(new Color(255, 228, 181));
        namePane.setVisible(false);
        this.add(namePane);

        secondNamePane = new JTextPane();

        secondNamePane.setText("Nazwisko");
        secondNamePane.setBounds((WINDOW_X_SIZE / 2) - 85, 320, 145, 70);
        secondNamePane.setBorder(BorderFactory.createLineBorder(Color.black));
        secondNamePane.setBackground(new Color(255, 228, 181));
        secondNamePane.setVisible(false);
        this.add(secondNamePane);

        idDrivingLPane = new JTextPane();

        idDrivingLPane.setText("id Prawo Jazdy");
        idDrivingLPane.setBounds((WINDOW_X_SIZE / 2) - 85, 390, 145, 70);
        idDrivingLPane.setBorder(BorderFactory.createLineBorder(Color.black));
        idDrivingLPane.setBackground(new Color(255, 228, 181));
        idDrivingLPane.setVisible(false);
        this.add(idDrivingLPane);


        categoryPane = new JTextPane();
        categoryPane.setText("Kategoria");
        categoryPane.setBounds((WINDOW_X_SIZE / 2) - 85, 460, 145, 70);
        categoryPane.setBorder(BorderFactory.createLineBorder(Color.black));
        categoryPane.setBackground(new Color(255, 228, 181));
        categoryPane.setVisible(false);
        this.add(categoryPane);

        addDrivingLButton = new MyButton("Dodaj prawo jazdy");
        addDrivingLButton.setBounds(300, 600, 145, 70);
        addDrivingLButton.setHorizontalAlignment(JTextField.CENTER);
        addDrivingLButton.setVisible(false);
        this.add(addDrivingLButton);

        addDrivingLButton.addActionListener(new ActionListener() {
            //on button click
            @Override
            public void actionPerformed(ActionEvent e) {

                if (namePane.getText() == "" || secondNamePane.getText() == "" || idDrivingLPane.getText() == "" || categoryPane.getText() == "" || (isNumeric(idDrivingLPane.getText()) == false) || (isNameorSecondName(namePane.getText())==false)||(isNameorSecondName(secondNamePane.getText())==false)) {
                    showMessageDialog(null, "Zle wypelnione dane");
                } else {
                    if (client.hasDrivingLicense() == false) {
                        try {
                            addDrivingLicense();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                        showMessageDialog(null, "Prawo jazdy dodano poprawnie");
                    } else {
                        showMessageDialog(null, "Prawo Jazdy juz dodane");

                    }
                }


            }
        });

        drivingLicenseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setDrivingLicenseView();

            }
        });


        vehicleListButton = new MyButton("Lista pojazdów");
        vehicleListButton.setBounds(375, 0, 125, 30);
        this.add(vehicleListButton);

        //handling renting a car and showing data service
        vehicleListButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(client.hasDrivingLicense()==true) {

                    setVehicleListView();

                    int pojazdListSize;
                    Session session;
                    session = sessionFactory.openSession();
                    session.beginTransaction();

                    List<Vehicle> vehicleFromDb = session.createQuery("from Vehicle ").list();
                    session.getTransaction().commit();
                    session.close();
                    pojazdListSize = vehicleFromDb.size();
                    System.out.println(pojazdListSize);
                    Object[][] data = new Object[pojazdListSize][5];
                    for (int i = 0; i < pojazdListSize; i++) {
                        data[i][0] = vehicleFromDb.get(i).getIdVehicle();
                        data[i][1] = vehicleFromDb.get(i).getPrice();
                        if (vehicleFromDb.get(i).isIsAvailable() == true)
                            data[i][2] = "dostepny";
                        else
                            data[i][2] = "niedostepny";

                        data[i][3] = vehicleFromDb.get(i).getIdVehicle();
                        data[i][4] = vehicleFromDb.get(i).getIdVehicle();


                    }
                    String columnHeaders[] = {"ID", "cena", "dostepnosc", "wypozycz", "dane"};


                    vehicleTable = new JTable(data, columnHeaders);

                    vehicleTable.getColumnModel().getColumn(3).setCellEditor(new RentButton(new JTextField(), client, sessionFactory, carRental));
                    vehicleTable.getColumnModel().getColumn(4).setCellEditor(new ShowDataButton(new JTextField(), client, sessionFactory));



                    vehicleTable.setRowHeight(30);

                    vehiclePanel.getViewport().setView(vehicleTable);
                    vehiclePanel.setVisible(true);
                    vehiclePanel.repaint();
                }


            }
        });
        BufferedImage myPicture = ImageIO.read(new File("G:\\MAS_PROJEKT_S18823\\src\\img\\img.png"));

        logoLabel = new JLabel(new ImageIcon(myPicture));

        logoLabel.setBounds(100, 225, 300, 151);
        logoLabel.setVisible(true);
        this.add(logoLabel);


        this.setSize(WINDOW_X_SIZE, WINDOW_Y_SIZE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(252, 242, 249));
        JFrame.setDefaultLookAndFeelDecorated(true);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setResizable(false);



    }
    //regex for category
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return idDrivingLicensePattern.matcher(strNum).matches();
    }
    public boolean isNameorSecondName(String expression){
        if (expression == null) {
            return false;
        }
        return nameAndSecondNAmePattern.matcher(expression).matches();
    }

    //setting vehicle list view
    public void setVehicleListView(){
        logoLabel.setVisible(false);
        namePane.setVisible(false);
        secondNamePane.setVisible(false);
        idDrivingLPane.setVisible(false);
        categoryPane.setVisible(false);
        addDrivingLButton.setVisible(false);
        vehiclePanel.setVisible(false);
        secondNamePane.setVisible(false);
        idDrivingLPane.setVisible(false);
        categoryPane.setVisible(false);
        addDrivingLButton.setVisible(false);
    }
    //setting drivinglicenseView
    public void setDrivingLicenseView(){
        logoLabel.setVisible(false);
        namePane.setVisible(true);
        secondNamePane.setVisible(true);
        idDrivingLPane.setVisible(true);
        categoryPane.setVisible(true);
        addDrivingLButton.setVisible(true);
        vehiclePanel.setVisible(false);
    }
    // handling renting service
    public void addDrivingLicense() throws Exception {
        String imie = namePane.getText();
        String nazwisko = secondNamePane.getText();
        System.out.println(imie);
        System.out.println(nazwisko);
        int idPrawoJazdy = Integer.parseInt(idDrivingLPane.getText());
        String kategoria = categoryPane.getText();

        System.out.println("Added prawo jazdy   " + imie + " " + nazwisko + " " + idPrawoJazdy + " " + kategoria);
//        DrivingLicense.createPrawoJazdy(imie,nazwisko,idPrawoJazdy,kategoria,klient)
        Session session;
        session = sessionFactory.openSession();


        session.beginTransaction();
        session.save(DrivingLicense.createDrivingLicense(imie,nazwisko,idPrawoJazdy,kategoria,klient));
        session.merge(klient);
        List<DrivingLicense> prawoJazdiesFromDb = session.createQuery("from DrivingLicense ").list();
        for (DrivingLicense drivingLicense1 : prawoJazdiesFromDb) { ;
            System.out.println(drivingLicense1);
        }
        session.getTransaction().commit();
        session.close();
    }



}





