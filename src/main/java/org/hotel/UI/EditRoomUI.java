package org.hotel.UI;

import org.hotel.Domain.Hotel_Admin;
import org.hotel.Domain.Room;
import org.hotel.Service.RoomService;

import javax.swing.*;
import java.awt.*;

public class EditRoomUI {

    private final RoomService roomService = new RoomService();

    public EditRoomUI(String id){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(6, 2, 15, 15));

        Room room = roomService.getRoomById(Integer.valueOf(id));

        JLabel fnamelb = new JLabel("ROOM_FLOOR");
        JTextField fnametf = new JTextField(room.getRoom_floor());

        JLabel lnamelb = new JLabel("CATEGORY");
        JTextField lnametf = new JTextField(room.getCategory());

        JLabel emaillb = new JLabel("URL");
        JTextField emailtf = new JTextField(room.getUrl());

        JLabel passlb = new JLabel("ROOM_PRICE");
        JTextField passtf = new JTextField(room.getRoom_price().toString());

        JLabel pricelb = new JLabel("HOTEL_ID");
        JTextField pricetf = new JTextField(room.getHotel_id().toString());

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
        frame.add(pricelb);
        frame.add(pricetf);
        frame.add(updateBtn);
        frame.add(backBtn);

        updateBtn.addActionListener(b->{
            roomService.UpdateAllValuesOfRoom(Integer.valueOf(id), fnametf.getText(), lnametf.getText(), emailtf.getText(), passtf.getText(), pricetf.getText());
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Room Updated!!!");
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
