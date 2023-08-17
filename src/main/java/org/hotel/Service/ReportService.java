package org.hotel.Service;

import org.hotel.DAO.CustomerDAO;
import org.hotel.DAO.HotelDAO;
import org.hotel.DAO.RoomDAO;
import org.hotel.Domain.Customer;
import org.hotel.Domain.Hotel;
import org.hotel.Domain.Room;

import java.util.List;

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

    public String[][] getAvailableRoom(Integer id, String a_date, String d_Date){
        RoomDAO roomDAO = new RoomDAO();
        List<Room> roomList = roomDAO.getAvailableRoom(id, a_date, d_Date);

        return convertValuesIntoJTableForRoom(roomList, 6);
    }

    private String[][] convertValuesIntoJTableForRoom(List<Room> roomList, int columnSize) {

        String[][] data = new String[roomList.size()][columnSize];

        for(int i=0; i<roomList.size(); i++){
            data[i][0] = String.valueOf(roomList.get(i).getId());
            data[i][1] = roomList.get(i).getRoom_floor();
            data[i][2] = roomList.get(i).getCategory();
            data[i][3] = roomList.get(i).getUrl();
            data[i][4] = String.valueOf(roomList.get(i).getRoom_price());
            data[i][5] = String.valueOf(roomList.get(i).getHotel_id());
        }

        return data;
    }

}
