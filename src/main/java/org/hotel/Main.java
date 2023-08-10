package org.hotel;

import org.hotel.DAO.HotelDAO;
import org.hotel.DAO.Hotel_AdminDAO;
import org.hotel.Domain.Hotel_Admin;

import org.hotel.UI.AddHotelAdminUI;
import org.hotel.UI.HotelAdminUI;
import org.hotel.UI.LoginUI;

public class Main {
    public static void main(String[] args) {

        new AddHotelAdminUI();


//        HotelDAO hotelDAO = new HotelDAO();
//        hotelDAO.getAll().forEach(System.out::println);

//        Hotel_AdminDAO hotelAdminDAO = new Hotel_AdminDAO();

//        hotelAdminDAO.getAll().forEach(System.out::println);

//        System.out.println(hotelAdminDAO.getById(3L));

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