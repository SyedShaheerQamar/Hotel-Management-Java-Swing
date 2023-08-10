package org.hotel.UI;

import org.hotel.Domain.Hotel_Admin;
import org.hotel.Service.HotelAdminService;

import javax.swing.*;
import java.awt.*;

public class EditHotelAdminUI {

    private final HotelAdminService hotelAdminService = new HotelAdminService();

    public EditHotelAdminUI(String id){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(5, 2, 15, 15));

        Hotel_Admin hotelAdmin = hotelAdminService.getHotelAdminById(Integer.valueOf(id));

        JLabel fnamelb = new JLabel("FIRST_NAME");
        JTextField fnametf = new JTextField(hotelAdmin.getFirst_name());

        JLabel lnamelb = new JLabel("LAST_NAME");
        JTextField lnametf = new JTextField(hotelAdmin.getLast_name());

        JLabel emaillb = new JLabel("EMAIL");
        JTextField emailtf = new JTextField(hotelAdmin.getEmail());

        JLabel passlb = new JLabel("PASSWORD");
        JTextField passtf = new JTextField(hotelAdmin.getPass());

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
            hotelAdminService.UpdateAllValues(Integer.valueOf(id), fnametf.getText(), lnametf.getText(), emailtf.getText(), passtf.getText());
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Hotel Admin Updated!!!");
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
