package org.hotel.UI;

import org.hotel.Service.HotelService;

import javax.swing.*;
import java.awt.*;

public class AddHotelUI {

    private final HotelService hotelService = new HotelService();

    public AddHotelUI() {
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(5, 2, 15, 15));

        JLabel fnamelb = new JLabel("HOTEL_NAME");
        JTextField fnametf = new JTextField();

        JLabel lnamelb = new JLabel("LOCATION");
        JTextField lnametf = new JTextField();

        JLabel emaillb = new JLabel("URL");
        JTextField emailtf = new JTextField();

        JLabel passlb = new JLabel("ADMIN_ID");
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

        addBtn.addActionListener(b -> {
            hotelService.insertIntoHotel(fnametf.getText(), lnametf.getText(), emailtf.getText(), passtf.getText());
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Hotel Added!!!");
            new HotelUI();
        });

        backBtn.addActionListener(b -> {
            frame.dispose();
            new HotelUI();
        });

        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}