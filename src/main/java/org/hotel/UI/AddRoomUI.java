package org.hotel.UI;

import org.hotel.Domain.Room;
import org.hotel.Service.RoomService;

import javax.swing.*;
import java.awt.*;

public class AddRoomUI {

    private final RoomService roomService = new RoomService();

    public AddRoomUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(6, 2, 15, 15));

        JLabel fnamelb = new JLabel("ROOM_FLOOR");
        JTextField fnametf = new JTextField();

        JLabel lnamelb = new JLabel("CATEGORY");
        JTextField lnametf = new JTextField();

        JLabel emaillb = new JLabel("URL");
        JTextField emailtf = new JTextField();

        JLabel passlb = new JLabel("ROOM_PRICE");
        JTextField passtf = new JTextField();

        JLabel pricelb = new JLabel("HOTEL_ID");
        JTextField pricetf = new JTextField();

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
        frame.add(pricelb);
        frame.add(pricetf);
        frame.add(addBtn);
        frame.add(backBtn);

        addBtn.addActionListener(b->{
            roomService.insertIntoRoom(fnametf.getText(), lnametf.getText(), emailtf.getText(), passtf.getText(), pricetf.getText());
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Room Added!!!");
            new RoomUI();
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new RoomUI();
        });

        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
