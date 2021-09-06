package View;

import Model.Client;
import Model.Vehicle;
import Model.CarRental;
import Model.Rent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentFrame extends JFrame {

    private int WINDOW_X_SIZE = 500;
    private int WINDOW_Y_SIZE = 300;

    private MyButton rentButton;
    private JTextPane dataFromPane;
    private JTextPane dateToPane;
    private JTextField rentText;
    private String dateFrom;
    private String dateTo;
    private Session session;


    public RentFrame(String title, SessionFactory sessionFactory, Client klient, Vehicle vehicle, CarRental carRental) {
        super(title);

        rentText = new JTextField();
        rentText.setText("Wypozycz pojazd");
        rentText.setBounds(150, 30, 200, 50);
        rentText.setEditable(false);
        rentText.setBackground(new Color(252, 242, 249));
        rentText.setBorder(new EmptyBorder(0, 0, 0, 0));
        rentText.setHorizontalAlignment(JTextField.CENTER);
        Font font1 = new Font("Calibri", Font.BOLD, 25);
        rentText.setFont(font1);

        this.add(rentText);

        dataFromPane = new JTextPane();
        dataFromPane.setText("Data od yyyy.MM.dd");
        dataFromPane.setBounds(75, 125, 100, 30);
        dataFromPane.setBorder(BorderFactory.createLineBorder(Color.black));
        dataFromPane.setBackground(new Color(255, 228, 181));


        this.add(dataFromPane);

        dateToPane = new JTextPane();
        dateToPane.setText("Data do yyyy.MM.dd");
        dateToPane.setBounds(325, 125, 100, 30);
        dateToPane.setBorder(BorderFactory.createLineBorder(Color.black));
        dateToPane.setBackground(new Color(255, 228, 181));

        this.add(dateToPane);

        rentButton = new MyButton("wypozycz");
        rentButton.setBounds(350, 175, 100, 30);
        this.add(rentButton);


        rentButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dateFrom = dataFromPane.getText();
                dateTo = dateToPane.getText();
                Date dateFromFormatted = new Date();
                Date dateToFormatted = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
                try {
                    dateFromFormatted = formatter.parse(dateFrom);
                    dateToFormatted = formatter.parse(dateTo);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
                System.out.println(dateToFormatted);
                System.out.println(dateFromFormatted);


                Rent rent = new Rent();
                rent.setDateFrom(dateFromFormatted);
                rent.setDateTo(dateToFormatted);
                klient.addRent(rent);
                rent.rent(vehicle);
                carRental.addRent(rent);
                double price = rent.calcCost(vehicle);

                System.out.println(rent.calcCost(vehicle));
                System.out.println(rent.calcPeriodOfRent());
                session = sessionFactory.openSession();
                session.beginTransaction();
                session.merge(carRental);
                session.merge(klient);
                session.merge(vehicle);
                session.save(rent);


                session.getTransaction().commit();
                session.close();
                PaymentFrame paymentFrame = new PaymentFrame("Płatnosc", price);

                dispose();

            }
        });


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
}

