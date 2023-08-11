package org.hotel.UI;

import org.hotel.Service.BookingService;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

public class AddBookingUI {

    private final BookingService bookingService = new BookingService();

    public AddBookingUI(){
        JFrame frame = new JFrame();

        JLabel hotellb = new JLabel("HOTEL");
        hotellb.setBounds(170, 30,150,30);

        String[] hotel = bookingService.get_Values_Hotel();
        JComboBox hotelBox = new JComboBox(hotel);
        hotelBox.setBounds(250, 30,150,30);

        JLabel roomlb = new JLabel("ROOM");
        roomlb.setBounds(170, 100,150,30);

        String[] room = bookingService.get_Values_Room();
        JComboBox roomBox = new JComboBox(room);
        roomBox.setBounds(250, 100,150,30);

        JLabel cuslb = new JLabel("CUSTOMER");
        cuslb.setBounds(170, 170,150,30);

        String[] customer = bookingService.get_Values_Customer();
        JComboBox customerBox = new JComboBox(customer);
        customerBox.setBounds(250, 170,150,30);

        JLabel pricelb = new JLabel("PRICE");
        pricelb.setBounds(170, 240,150,30);

        JTextField pricetf = new JTextField();
        pricetf.setBounds(250, 240,150,30);

        JLabel datelb = new JLabel("ARRIVAL_DATE");
        datelb.setBounds(130, 310, 150, 30);

        UtilDateModel model = new UtilDateModel();
        model.setDate(model.getYear(), model.getMonth(), model.getDay());
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        datePicker.setBounds(250, 310, 150, 30);

        JLabel d_datelb = new JLabel("DEPARTURE_DATE");
        d_datelb.setBounds(130, 380, 150, 30);

        UtilDateModel d_model = new UtilDateModel();
        model.setDate(model.getYear(), model.getMonth(), model.getDay());
        Properties p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(d_model, p1);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
        datePicker2.setBounds(250, 380, 150, 30);

        LocalDate now = LocalDate.now();


        JButton addBooking = new JButton("ADD");
        addBooking.setBounds(130, 450, 120, 30);

        JButton back = new JButton("BACK");
        back.setBounds(300, 450, 120, 30);

        addBooking.addActionListener(b->{
            String h = (String) hotelBox.getSelectedItem();
            String r = (String) roomBox.getSelectedItem();
            String c = (String) customerBox.getSelectedItem();
            Integer h_id = Character.getNumericValue(h.charAt(0));
            Integer v_id = Character.getNumericValue(r.charAt(0));
            Integer c_id = Character.getNumericValue(c.charAt(0));

            LocalDate arr_date = LocalDate.of(model.getYear(), (model.getMonth()+1), model.getDay());
            LocalDate dep_date = LocalDate.of(d_model.getYear(), (d_model.getMonth()+1), d_model.getDay());

            if(arr_date.isBefore(now) || dep_date.isBefore(arr_date)){
                JOptionPane.showMessageDialog(frame, "Pick a valid date!!!");
            }
            else {
                if (pricetf.getText().length() == 0){
                    JOptionPane.showMessageDialog(frame, "Please Input Price!!!");
                }
                else {
                    String a_date = model.getYear() + "-" + (model.getMonth()+1) + "-" + model.getDay();
                    String d_date = d_model.getYear() + "-" + (d_model.getMonth()+1) + "-" + d_model.getDay();

                    bookingService.insertIntoBooking(h_id, v_id, c_id, pricetf.getText(), a_date, d_date);
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "Booking Added!!!");
                    new BookingUI();
                }
            }

        });

        back.addActionListener(b->{
            frame.dispose();
            new BookingUI();
        });

        frame.add(hotellb);
        frame.add(hotelBox);
        frame.add(roomlb);
        frame.add(roomBox);
        frame.add(cuslb);
        frame.add(customerBox);
        frame.add(pricelb);
        frame.add(pricetf);
        frame.add(datelb);
        frame.add(datePicker);
        frame.add(d_datelb);
        frame.add(datePicker2);
        frame.add(addBooking);
        frame.add(back);

        frame.setSize(600, 620);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
