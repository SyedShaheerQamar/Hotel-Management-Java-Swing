package org.hotel;

import com.itextpdf.text.Document;
import com.mysql.cj.log.Log;
import org.hotel.DAO.BookingDAO;
import org.hotel.Domain.Booking;
import org.hotel.Domain.Room;
import org.hotel.UI.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        new MonthlyReportUI();

//        HotelDAO hotelDAO = new HotelDAO();
//        hotelDAO.getAll().forEach(System.out::println);

//        Hotel_AdminDAO hotelAdminDAO = new Hotel_AdminDAO();

//        hotelAdminDAO.getAll().forEach(System.out::println);

//        System.ou t.println(hotelAdminDAO.getById(3L));

//        Hotel_Admin ha = Hotel_Admin.builder()
//                .first_name("Shaheer")
//                .last_name("Qamar")
//                .email("shaheer@gmail.com")
//                .pass("sha@123")
//                .build();
//
//        hotelAdminDAO.insert(ha);

//        Hotel_Admin ha = Hotel_Admin.builder()
//                .email("basim_098@gmail.com")
//                .build();
//
//        hotelAdminDAO.update(ha, 2L);

//        hotelAdminDAO.deleteById(4L);


    }
}