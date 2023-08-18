package org.hotel.UI;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hotel.Service.BookingService;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class MonthlyReportUI {


    private static Integer count = 0;

    private static String FILE = "D:/Java/MonthlyReport.pdf";

    private final BookingService bookingService = new BookingService();

    public MonthlyReportUI(){
        JFrame frame = new JFrame();

        JLabel datelb = new JLabel("ARRIVAL_DATE");
        datelb.setBounds(230, 50, 150, 30);

        UtilDateModel model = new UtilDateModel();
        model.setDate(model.getYear(), model.getMonth(), model.getDay());
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl dateP = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(dateP, new DateComponentFormatter());

        datePicker.setBounds(350, 50, 150, 30);

        JLabel d_datelb = new JLabel("DEPARTURE_DATE");
        d_datelb.setBounds(230, 120, 150, 30);

        UtilDateModel d_model = new UtilDateModel();
        model.setDate(model.getYear(), model.getMonth(), model.getDay());
        Properties p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(d_model, p1);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
        datePicker2.setBounds(350, 120, 150, 30);

        JLabel hotellb = new JLabel("HOTEL");
        hotellb.setBounds(230, 190,150,30);

        String[] hotel = bookingService.getValuesHotel();
        JComboBox hotelBox = new JComboBox(hotel);
        hotelBox.setBounds(350, 190,150,30);

        JButton generateBtn = new JButton("Generate Report");
        generateBtn.setBounds(650, 250, 150, 30);

        JButton pdfBtn = new JButton("Generate PDF");
        pdfBtn.setBounds(650, 300, 150, 30);

        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(650, 350, 150, 30);

        String[][] data = null;
        String[] columns = {"ID", "H_ID", "R_ID", "C_ID", "PRICE", "A_DATE", "D_DATE", "STATUS"};

        DefaultTableModel dtm = new DefaultTableModel(data, columns);

        JTable jTable = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(30, 250, 550, 300);

        LocalDate now = LocalDate.now();

        JLabel reportlb = new JLabel();
        reportlb.setBounds(30, 570, 500, 30);
        reportlb.setForeground(Color.black);
        reportlb.setBackground(Color.gray);


        generateBtn.addActionListener(b->{
            String h = (String) hotelBox.getSelectedItem();
            String[] h_value = h.split(",");
            Integer h_id = Integer.valueOf(h_value[0]);

            LocalDate arr_date = LocalDate.of(model.getYear(), (model.getMonth()+1), model.getDay());
            LocalDate dep_date = LocalDate.of(d_model.getYear(), (d_model.getMonth()+1), d_model.getDay());

            if(dep_date.isBefore(arr_date)){
                JOptionPane.showMessageDialog(frame, "Pick a valid date!!!");
            }
            else {
                String a_date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
                String d_date = d_model.getYear() + "-" + (d_model.getMonth() + 1) + "-" + d_model.getDay();

                String[][] data2 = bookingService.MonthlyReportBooking(a_date, d_date, h_id);
                if(data2.length == 0){
                    DefaultTableModel dtm2 = new DefaultTableModel(null, columns);
                    jTable.setModel(dtm2);
                    reportlb.setText("");
                    JOptionPane.showMessageDialog(frame, "No rooms booked in this hotel!");
                }
                else {
                    DefaultTableModel dtm2 = new DefaultTableModel(data2, columns);
                    jTable.setModel(dtm2);

                    reportlb.setText(bookingService.getTotalPrice(a_date, d_date, h_id));
                }

            }
        });


        pdfBtn.addActionListener(b->{
            if (jTable.getRowCount() <= 0){
                reportlb.setText("");
                JOptionPane.showMessageDialog(frame, "No report available!!!");
            }
            else {
                count += 1;
                FILE = "D:/Java/MonthlyReport" + count + ".pdf";
                String result = reportlb.getText();
                String a_date = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
                String d_date = d_model.getYear() + "-" + (d_model.getMonth() + 1) + "-" + d_model.getDay();
                try {
                    Document doc = new Document();
                    PdfWriter.getInstance(doc, new FileOutputStream(FILE));
                    doc.open();
                    addMetaData(doc);
                    createTable(doc, jTable, result,a_date, d_date);
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
        });


        backBtn.addActionListener(b->{
            frame.dispose();
            new ReportUI();
        });

        frame.add(datelb);
        frame.add(datePicker);
        frame.add(d_datelb);
        frame.add(datePicker2);
        frame.add(hotellb);
        frame.add(hotelBox);
        frame.add(sp);
        frame.add(generateBtn);
        frame.add(pdfBtn);
        frame.add(backBtn);
        frame.add(reportlb);

        frame.setSize(900, 720);
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

    private static void createTable(Document document, JTable jTable, String res, String adate, String ddate) throws DocumentException {
        Anchor anchor = new Anchor("Monthly Report of Hotel : "+jTable.getValueAt(0, 1));
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph Para = new Paragraph();
        Para.add("The report is from "+adate+" till "+ddate);

        Paragraph nePara = new Paragraph();
        addEmptyLine(nePara, 3);

        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("H_ID"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("A_DATE"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("D_DATE"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("PRICE"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        table.setHeaderRows(1);

        for(int i=0; i<jTable.getRowCount(); i++){
            table.addCell((String) jTable.getValueAt(i, 1));
            table.addCell((String) jTable.getValueAt(i, 5));
            table.addCell((String) jTable.getValueAt(i, 6));
            table.addCell((String) jTable.getValueAt(i, 4));
        }

        catPart.add(Para);
        catPart.add(nePara);
        catPart.add(table);
        catPart.add(nePara);
        catPart.add(new Paragraph(res));

        document.add(catPart);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
