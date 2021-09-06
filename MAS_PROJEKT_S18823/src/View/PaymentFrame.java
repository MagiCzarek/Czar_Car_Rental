package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentFrame extends JFrame {
    private int WINDOW_X_SIZE = 500;
    private int WINDOW_Y_SIZE = 300;
    private JTextField paymentTextArea;
    private JButton paymentButton;

    public PaymentFrame(String title, double price) throws HeadlessException {
        super(title);

        paymentTextArea = new JTextField();
        paymentTextArea.setText(String.valueOf(price));
        paymentTextArea.setBounds(150, 30, 200, 50);
        paymentTextArea.setEditable(false);
        paymentTextArea.setBackground(new Color(252, 242, 249));
        paymentTextArea.setBorder(new EmptyBorder(0, 0, 0, 0));
        paymentTextArea.setHorizontalAlignment(JTextField.CENTER);
        Font font1 = new Font("Calibri", Font.BOLD, 25);
        paymentTextArea.setFont(font1);

        this.add(paymentTextArea);

        // action on button payment
        paymentButton = new MyButton("zaplac");
        paymentButton.setBounds(200, 130, 100, 30);
        this.add(paymentButton);
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
