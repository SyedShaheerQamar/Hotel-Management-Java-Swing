package org.hotel.UI;

import javax.swing.*;
import java.awt.*;

public class HomeUI {

    public HomeUI(){
        JFrame frame = new JFrame("HOTEL APP - HOME");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 90));

        JButton hotelAdminBtn = new JButton("HOTEL_ADMIN");
        JButton hotelBtn = new JButton("HOTEL");
        JButton roomBtn = new JButton("ROOM");
        JButton customerBtn = new JButton("CUSTOMER");
        JButton bookingBtn = new JButton("BOOKING");

        hotelAdminBtn.setPreferredSize(new Dimension(200, 100));
        hotelBtn.setPreferredSize(new Dimension(200, 100));
        roomBtn.setPreferredSize(new Dimension(200, 100));
        customerBtn.setPreferredSize(new Dimension(200, 100));
        bookingBtn.setPreferredSize(new Dimension(200, 100));

        hotelAdminBtn.addActionListener(b->{
            frame.dispose();
            new HotelAdminUI();
        });

        hotelBtn.addActionListener(b->{
            frame.dispose();
            new HotelUI();
        });

        roomBtn.addActionListener(b->{
            frame.dispose();
            new RoomUI();
        });

        customerBtn.addActionListener(b->{
            frame.dispose();
            new CustomerUI();
        });

        bookingBtn.addActionListener(b->{
            frame.dispose();
            new BookingUI();
        });

        frame.add(hotelAdminBtn);
        frame.add(hotelBtn);
        frame.add(roomBtn);
        frame.add(customerBtn);
        frame.add(bookingBtn);

        frame.setSize(800, 620);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
