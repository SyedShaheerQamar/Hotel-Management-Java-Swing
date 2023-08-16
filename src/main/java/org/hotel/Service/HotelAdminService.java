package org.hotel.Service;

import org.hotel.DAO.HotelDAO;
import org.hotel.DAO.Hotel_AdminDAO;
import org.hotel.Domain.Booking;
import org.hotel.Domain.Hotel;
import org.hotel.Domain.Hotel_Admin;

import java.util.List;

public class HotelAdminService {

    Hotel_AdminDAO dao = new Hotel_AdminDAO();

    public Hotel_Admin getHotelAdminById(Integer id){
        return dao.getById(Long.valueOf(id));
    }

    public void deleteById(String id){
        dao.deleteById(Long.valueOf(id));
    }

    public void UpdateAllValues(Integer id,String fname, String lname, String email, String pass){
        Hotel_Admin hotelAdmin = Hotel_Admin.builder()
                .first_name(fname)
                .last_name(lname)
                .email(email)
                .pass(pass)
                .build();

        dao.updateAll(hotelAdmin, id);
    }

    public String[][] searchByNameInHotelAdmin(String name){

        List<Hotel_Admin> hotel_adminList = dao.searchByName(name);
        return convertValuesIntoJTable(hotel_adminList, 5);

    }

    public void insertIntoHotelAdmin(String fname, String lname, String email, String pass){
        Hotel_Admin hotelAdmin = Hotel_Admin.builder()
                .first_name(fname)
                .last_name(lname)
                .email(email)
                .pass(pass)
                .build();

        dao.insert(hotelAdmin);
    }

    public String[][] getAllValuesOfHotelAdmin(){

        List<Hotel_Admin> hotel_adminList = dao.getAll();
        return convertValuesIntoJTable(hotel_adminList, 5);
    }

    private String[][] convertValuesIntoJTable(List<Hotel_Admin> hotelAdminList, int columnSize) {

        String[][] data = new String[hotelAdminList.size()][columnSize];

        for(int i=0; i<hotelAdminList.size(); i++){
            data[i][0] = String.valueOf(hotelAdminList.get(i).getId());
            data[i][1] = hotelAdminList.get(i).getFirst_name();
            data[i][2] = hotelAdminList.get(i).getLast_name();
            data[i][3] = hotelAdminList.get(i).getEmail();
            data[i][4] = hotelAdminList.get(i).getPass();
        }

        return data;
    }

    public Boolean checkId(Integer id){
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> hotelList = hotelDAO.getAll();

        for(int i=0; i<hotelList.size(); i++){
            if (id.equals(hotelList.get(i).getAdmin_id())){
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

}
