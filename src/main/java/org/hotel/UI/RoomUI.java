package org.hotel.UI;

import org.hotel.Service.RoomService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RoomUI {

    private final RoomService roomService = new RoomService();

    public RoomUI(){
        JFrame frame = new JFrame("HOTEL APP - ROOM");
        frame.setLayout(new BorderLayout(20, 20));

        // search bar
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 30));
        JTextField searchtf = new JTextField(40);
        searchPanel.add(searchtf);

        // table for showing values
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

        String[][] data = roomService.getAllValuesOfRoom();
        String[] columns = {"ID", "R_FLOOR", "CATEGORY", "URL", "ROOM_PRICE", "H_ID"};

        DefaultTableModel dtm = new DefaultTableModel(data, columns);

        JTable jTable = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jTable);
        sp.setPreferredSize(new Dimension(550, 400));

        tablePanel.add(sp);

        searchtf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String[][] data = roomService.searchByNameInHotel(searchtf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, columns);
                jTable.setModel(dtm);
            }
        });

        // buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("ADD");
        JButton editButton = new JButton("EDIT");
        JButton deleteButton = new JButton("DELETE");
        JButton backButton = new JButton("BACK");

        btnPanel.add(addButton);
        btnPanel.add(editButton);
        btnPanel.add(deleteButton);
        btnPanel.add(backButton);

        addButton.addActionListener(b->{
            frame.dispose();
            new AddRoomUI();
        });

        editButton.addActionListener(b->{
            Integer val = jTable.getSelectedRow();

            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a row!!!");
            }
            else {
                String id = (String) jTable.getValueAt(val, 0);
                frame.dispose();
                new EditRoomUI(id);
            }
        });

        deleteButton.addActionListener(b->{
            Integer val = jTable.getSelectedRow();

            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a row!!!");
            }
            else {
                String id = (String) jTable.getValueAt(val, 0);
                roomService.deleteById(id);
                JOptionPane.showMessageDialog(frame, "Deleted!!!");
                frame.dispose();
                new RoomUI();
            }
        });

        backButton.addActionListener(b->{
            frame.dispose();
            new HomeUI();
        });


        // adding into frame
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.WEST);
        frame.add(btnPanel, BorderLayout.EAST);

        frame.setSize(1000, 620);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
