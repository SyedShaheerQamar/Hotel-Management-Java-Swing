package org.hotel.Service;

import org.hotel.DAO.HotelDAO;
import org.hotel.DAO.RoomDAO;
import org.hotel.Domain.Hotel;
import org.hotel.Domain.Room;

import java.util.List;

public class RoomService {

    RoomDAO dao = new RoomDAO();

    public Room getRoomById(Integer id){

        return dao.getById(Long.valueOf(id));

    }

    public void deleteById(String id){

        dao.deleteById(Long.valueOf(id));

    }

    public void UpdateAllValuesOfRoom(Integer id,String roomF, String cat, String url, String pri, String h_id){
        Room room = Room.builder()
                .room_floor(roomF)
                .category(cat)
                .url(url)
                .room_price(Integer.valueOf(pri))
                .hotel_id(Integer.valueOf(h_id))
                .build();

        dao.updateAllValues(room, id);
    }

    public String[][] searchByNameInHotel(String name){

        List<Room> roomList = dao.searchByName(name);
        return convertValuesIntoJTable(roomList, 6);

    }

    public void insertIntoRoom(String roomF, String cat, String url, String pri, String h_id){
        Room room = Room.builder()
                .room_floor(roomF)
                .category(cat)
                .url(url)
                .room_price(Integer.valueOf(pri))
                .hotel_id(Integer.valueOf(h_id))
                .build();

        dao.insert(room);
    }

    public String[][] getAllValuesOfRoom(){

        List<Room> roomList = dao.getAll();
        return convertValuesIntoJTable(roomList, 6);
    }

    private String[][] convertValuesIntoJTable(List<Room> roomList, int columnSize) {

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
