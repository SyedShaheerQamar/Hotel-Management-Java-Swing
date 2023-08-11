package org.hotel.Service;

import org.hotel.DAO.HotelDAO;
import org.hotel.DAO.Hotel_AdminDAO;
import org.hotel.Domain.Hotel;
import org.hotel.Domain.Hotel_Admin;

import java.util.List;

public class HotelService {
    HotelDAO dao = new HotelDAO();

    public Hotel getHotelById(Integer id){

        return dao.getById(Long.valueOf(id));

    }

    public void deleteById(String id){
        dao.deleteById(Long.valueOf(id));
    }

    public void UpdateAllValuesOfHotel(Integer id,String hname, String location, String url, String ad_id){
        Hotel hotel = Hotel.builder()
                .hotel_name(hname)
                .location(location)
                .url(url)
                .admin_id(Integer.valueOf(ad_id))
                .build();

        dao.updateAllValues(hotel, id);
    }

    public String[][] searchByNameInHotel(String name){

        List<Hotel> hotelList = dao.searchByName(name);
        return convertValuesIntoJTable(hotelList, 5);

    }

    public void insertIntoHotel(String hname, String location, String url, String ad_id){
        Hotel hotel = Hotel.builder()
                .hotel_name(hname)
                .location(location)
                .url(url)
                .admin_id(Integer.valueOf(ad_id))
                .build();

        dao.insert(hotel);
    }

    public String[][] getAllValuesOfHotel(){

        List<Hotel> hotelList = dao.getAll();
        return convertValuesIntoJTable(hotelList, 5);
    }

    private String[][] convertValuesIntoJTable(List<Hotel> hotelList, int columnSize) {

        String[][] data = new String[hotelList.size()][columnSize];

        for(int i=0; i<hotelList.size(); i++){
            data[i][0] = String.valueOf(hotelList.get(i).getId());
            data[i][1] = hotelList.get(i).getHotel_name();
            data[i][2] = hotelList.get(i).getLocation();
            data[i][3] = hotelList.get(i).getUrl();
            data[i][4] = String.valueOf(hotelList.get(i).getAdmin_id());
        }

        return data;
    }
}
