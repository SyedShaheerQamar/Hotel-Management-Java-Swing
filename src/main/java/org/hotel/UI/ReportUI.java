package org.hotel.UI;

import javax.swing.*;
import java.awt.*;

public class ReportUI {

    public ReportUI(){
        JFrame frame = new JFrame();

        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 80));

        JButton reportBtn = new JButton("MONTHLY_REPORT");
//        JButton hotelBtn = new JButton("");
        JButton roomBtn = new JButton("ROOM AVAILABILITY");
        JButton backBtn = new JButton("BACK");


        reportBtn.setPreferredSize(new Dimension(200, 100));
//        hotelBtn.setPreferredSize(new Dimension(200, 100));
        roomBtn.setPreferredSize(new Dimension(200, 100));
        backBtn.setPreferredSize(new Dimension(200, 100));

        reportBtn.addActionListener(b->{
            frame.dispose();
            new MonthlyReportUI();
        });

        roomBtn.addActionListener(b->{
            frame.dispose();
            new RoomAvailabiltyUI();
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new HomeUI();
        });

        frame.add(reportBtn);
//        frame.add(hotelBtn);
        frame.add(roomBtn);
        frame.add(backBtn);

        frame.setSize(900, 380);
        frame.setBackground(Color.gray);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
