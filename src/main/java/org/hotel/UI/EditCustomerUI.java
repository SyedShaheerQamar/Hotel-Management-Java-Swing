package org.hotel.UI;

import org.hotel.Domain.Customer;
import org.hotel.Domain.Hotel;
import org.hotel.Service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class EditCustomerUI {

    private final CustomerService customerService = new CustomerService();

    public EditCustomerUI(String id){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(4, 2, 15, 15));

        Customer customer = customerService.getCustomerById(Integer.valueOf(id));

        JLabel fnamelb = new JLabel("CUSTOMER_NAME");
        JTextField fnametf = new JTextField(customer.getName());

        JLabel lnamelb = new JLabel("PHONE_NUMBER");
        JTextField lnametf = new JTextField(customer.getPhone());

        JLabel emaillb = new JLabel("CNIC");
        JTextField emailtf = new JTextField(customer.getCnic());

        JButton updateBtn = new JButton("UPDATE");
        JButton backBtn = new JButton("BACK");

        frame.add(fnamelb);
        frame.add(fnametf);
        frame.add(lnamelb);
        frame.add(lnametf);
        frame.add(emaillb);
        frame.add(emailtf);
        frame.add(updateBtn);
        frame.add(backBtn);

        updateBtn.addActionListener(b->{
            customerService.UpdateAllValuesOfCustomer(Integer.valueOf(id), fnametf.getText(), lnametf.getText(), emailtf.getText());
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Customer Updated!!!");
            new CustomerUI();
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new CustomerUI();
        });

        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
