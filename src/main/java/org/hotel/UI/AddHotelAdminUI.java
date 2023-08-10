package org.hotel.UI;

import org.hotel.Service.HotelAdminService;

import javax.swing.*;
import java.awt.*;

public class AddHotelAdminUI {

    private final HotelAdminService hotelAdminService = new HotelAdminService();

    public AddHotelAdminUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(5, 2, 15, 15));

        JLabel fnamelb = new JLabel("FIRST_NAME");
        JTextField fnametf = new JTextField();

        JLabel lnamelb = new JLabel("LAST_NAME");
        JTextField lnametf = new JTextField();

        JLabel emaillb = new JLabel("EMAIL");
        JTextField emailtf = new JTextField();

        JLabel passlb = new JLabel("PASSWORD");
        JTextField passtf = new JTextField();

        JButton addBtn = new JButton("ADD");
        JButton backBtn = new JButton("BACK");

        frame.add(fnamelb);
        frame.add(fnametf);
        frame.add(lnamelb);
        frame.add(lnametf);
        frame.add(emaillb);
        frame.add(emailtf);
        frame.add(passlb);
        frame.add(passtf);
        frame.add(addBtn);
        frame.add(backBtn);

        addBtn.addActionListener(b->{
            hotelAdminService.insertIntoHotelAdmin(fnametf.getText(), lnametf.getText(), emailtf.getText(), passtf.getText());
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Hotel Admin Added!!!");
            new HotelAdminUI();
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new HotelAdminUI();
        });

        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


    }
}
