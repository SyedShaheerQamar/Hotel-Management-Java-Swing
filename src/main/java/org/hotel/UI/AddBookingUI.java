package org.hotel.UI;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hotel.Service.BookingService;
import org.hotel.Service.ReportService;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

public class AddBookingUI {

    private static Integer count = 0;

    private static String FILE = "D:/Java/BookingVoucher.pdf";

    private final BookingService bookingService = new BookingService();

    public AddBookingUI(){
        JFrame frame = new JFrame();

        JLabel datelb = new JLabel("ARRIVAL_DATE");
        datelb.setBounds(130, 30, 150, 30);

        UtilDateModel model = new UtilDateModel();
        model.setDate(model.getYear(), model.getMonth(), model.getDay());
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        datePicker.setBounds(250, 30, 150, 30);

        JLabel d_datelb = new JLabel("DEPARTURE_DATE");
        d_datelb.setBounds(130, 100, 150, 30);

        UtilDateModel d_model = new UtilDateModel();
        model.setDate(model.getYear(), model.getMonth(), model.getDay());
        Properties p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(d_model, p1);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
        datePicker2.setBounds(250, 100, 150, 30);

        JLabel hotellb = new JLabel("HOTEL");
        hotellb.setBounds(170, 170,150,30);

        String[] hotel = bookingService.get_Values_Hotel();
        JComboBox hotelBox = new JComboBox(hotel);
        hotelBox.setBounds(250, 170,150,30);

        JButton checkBtn = new JButton("Check Availability");
        checkBtn.setBounds(200, 240,150,30);

        JLabel roomlb = new JLabel("ROOM");
        roomlb.setBounds(170, 310,150,30);

        String[] room = {};
        JComboBox roomBox = new JComboBox(room);
        roomBox.setBounds(250, 310,150,30);

        JLabel cuslb = new JLabel("CUSTOMER");
        cuslb.setBounds(170, 380,150,30);

        String[] customer = bookingService.get_Values_Customer();
        JComboBox customerBox = new JComboBox(customer);
        customerBox.setBounds(250, 380,150,30);

        LocalDate now = LocalDate.now();

        JButton addBooking = new JButton("ADD");
        addBooking.setBounds(130, 450, 120, 30);

        JButton back = new JButton("BACK");
        back.setBounds(300, 450, 120, 30);

        checkBtn.addActionListener(b->{
            LocalDate arr_date = LocalDate.of(model.getYear(), (model.getMonth()+1), model.getDay());
            LocalDate dep_date = LocalDate.of(d_model.getYear(), (d_model.getMonth()+1), d_model.getDay());

            if(arr_date.isBefore(now) || dep_date.isBefore(arr_date)){
                JOptionPane.showMessageDialog(frame, "Pick a valid date!!!");
            }
            else {
                String h = (String) hotelBox.getSelectedItem();
                String[] h_value = h.split(",");
                Integer h_id = Integer.valueOf(h_value[0]);

                String a_date = model.getYear() + "-" + (model.getMonth()+1) + "-" + model.getDay();
                String d_date = d_model.getYear() + "-" + (d_model.getMonth()+1) + "-" + d_model.getDay();

                roomBox.removeAllItems();

                String[] data = bookingService.checkAvailability(h_id, a_date, d_date);

                if(data[0].equalsIgnoreCase("none")){
                    JOptionPane.showMessageDialog(frame, "No rooms available for this hotel!!!");
                }
                else {
                    for(int i=0; i<data.length; i++){
                        roomBox.addItem(data[i]);
                    }
                }
            }
        });

        addBooking.addActionListener(b->{
            String h = (String) hotelBox.getSelectedItem();
            String r = (String) roomBox.getSelectedItem();
            String c = (String) customerBox.getSelectedItem();
            if(r == null){
                JOptionPane.showMessageDialog(frame, "No rooms avialable!!!");
            }
            else {
                String[] h_value = h.split(",");
                String[] r_value = r.split(",");
                String[] c_value = c.split(",");
                Integer h_id = Integer.valueOf(h_value[0]);
                Integer r_id = Integer.valueOf(r_value[0]);
                Integer c_id = Integer.valueOf(c_value[0]);

                LocalDate arr_date = LocalDate.of(model.getYear(), (model.getMonth() + 1), model.getDay());
                LocalDate dep_date = LocalDate.of(d_model.getYear(), (d_model.getMonth() + 1), d_model.getDay());

                if (arr_date.isBefore(now) || dep_date.isBefore(arr_date)) {
                    JOptionPane.showMessageDialog(frame, "Pick a valid date!!!");
                } else {
                    String a_date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
                    String d_date = d_model.getYear() + "-" + (d_model.getMonth() + 1) + "-" + d_model.getDay();

                    bookingService.insertIntoBooking(h_id, r_id, c_id, a_date, d_date);
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "Booking Added!!!");
                    new BookingUI();

                    count += 1;
                    FILE = "D:/Java/BookingVoucher" + count + ".pdf";

                    try {
                        Document doc = new Document();
                        PdfWriter.getInstance(doc, new FileOutputStream(FILE));
                        doc.open();
                        addMetaData(doc);
                        addContent(doc, a_date, d_date, h_id, r_id, c_id);
                        doc.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    File file = new File(FILE);
                    if (file.toString().endsWith(".pdf")) {
                        try {
                            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.open(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
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
        frame.add(checkBtn);
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

    private static void addMetaData(Document document) {
        document.addTitle("Monthly Report");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private static void addContent(Document document, String adate, String ddate, Integer h_id, Integer r_id, Integer c_id) throws DocumentException {
        ReportService bookingService1 = new ReportService();
        String rName = bookingService1.getRoomName(r_id);
        String hName = bookingService1.getHotelName(h_id);
        String cName = bookingService1.getCustomerName(c_id);

        Anchor anchor = new Anchor("Booking Voucher for Customer "+cName);
        anchor.setName("First Chapter");

        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph Para = new Paragraph();
        Para.add("Arrival Date "+adate+"\n\nDeparture Date "+ddate+"\n\nHotel : "+hName+"\n\nRoom : "+rName+"\n\nCustomer : "+cName);


        Paragraph nePara = new Paragraph();
        addEmptyLine(nePara, 2);

        catPart.add(nePara);
        catPart.add(Para);
        catPart.add(nePara);

        document.add(catPart);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
