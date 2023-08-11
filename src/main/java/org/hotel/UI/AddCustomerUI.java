package org.hotel.UI;

import org.hotel.Service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class AddCustomerUI {

    private final CustomerService customerService = new CustomerService();

    public AddCustomerUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(4, 2, 15, 15));

        JLabel fnamelb = new JLabel("CUSTOMER_NAME");
        JTextField fnametf = new JTextField();

        JLabel lnamelb = new JLabel("PHONE_NUMBER");
        JTextField lnametf = new JTextField();

        JLabel emaillb = new JLabel("CNIC");
        JTextField emailtf = new JTextField();

        JButton addBtn = new JButton("ADD");
        JButton backBtn = new JButton("BACK");

        frame.add(fnamelb);
        frame.add(fnametf);
        frame.add(lnamelb);
        frame.add(lnametf);
        frame.add(emaillb);
        frame.add(emailtf);
        frame.add(addBtn);
        frame.add(backBtn);

        addBtn.addActionListener(b -> {
            customerService.insertIntoCustomer(fnametf.getText(), lnametf.getText(), emailtf.getText());
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Customer Added!!!");
            new CustomerUI();
        });

        backBtn.addActionListener(b -> {
            frame.dispose();
            new CustomerUI();
        });

        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
