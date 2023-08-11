package org.hotel.UI;

import org.hotel.Domain.Hotel;
import org.hotel.Service.HotelService;

import javax.swing.*;
import java.awt.*;

public class EditHotelUI {

    private final HotelService hotelService = new HotelService();

    public EditHotelUI(String id){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(5, 2, 15, 15));

        Hotel hotel = hotelService.getHotelById(Integer.valueOf(id));

        JLabel fnamelb = new JLabel("HOTEL_NAME");
        JTextField fnametf = new JTextField(hotel.getHotel_name());

        JLabel lnamelb = new JLabel("LOCATION");
        JTextField lnametf = new JTextField(hotel.getLocation());

        JLabel emaillb = new JLabel("URL");
        JTextField emailtf = new JTextField(hotel.getUrl());

        JLabel passlb = new JLabel("ADMIN_ID");
        JTextField passtf = new JTextField(hotel.getAdmin_id().toString());

        JButton updateBtn = new JButton("UPDATE");
        JButton backBtn = new JButton("BACK");

        frame.add(fnamelb);
        frame.add(fnametf);
        frame.add(lnamelb);
        frame.add(lnametf);
        frame.add(emaillb);
        frame.add(emailtf);
        frame.add(passlb);
        frame.add(passtf);
        frame.add(updateBtn);
        frame.add(backBtn);

        updateBtn.addActionListener(b->{
            hotelService.UpdateAllValuesOfHotel(Integer.valueOf(id), fnametf.getText(), lnametf.getText(), emailtf.getText(), passtf.getText());
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Hotel Updated!!!");
            new HotelUI();
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new HotelUI();
        });

        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
