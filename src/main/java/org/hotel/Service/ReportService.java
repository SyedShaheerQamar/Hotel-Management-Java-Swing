package org.hotel.Service;

import org.hotel.DAO.CustomerDAO;
import org.hotel.DAO.HotelDAO;
import org.hotel.DAO.RoomDAO;
import org.hotel.Domain.Customer;
import org.hotel.Domain.Hotel;
import org.hotel.Domain.Room;

public class ReportService {

    public String getRoomName(Integer r_id){
        RoomDAO roomDAO = new RoomDAO();
        Room room = roomDAO.getById(Long.valueOf(r_id));

        String roomName = room.getRoom_floor() +"  "+ room.getCategory();
        return roomName;
    }

    public String getHotelName(Integer h_id){
        HotelDAO hotelDAO = new HotelDAO();
        Hotel hotel = hotelDAO.getById(Long.valueOf(h_id));

        return hotel.getHotel_name();
    }

    public String getCustomerName(Integer c_id){
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getById(Long.valueOf(c_id));

        return customer.getName();
    }

}
