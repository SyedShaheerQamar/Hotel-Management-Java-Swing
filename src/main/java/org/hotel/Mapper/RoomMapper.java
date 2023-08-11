package org.hotel.Mapper;

import org.hotel.Domain.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomMapper implements iMapper<Room>{

    private static final String ID = "ID";
    private static final String ROOM_FLOOR = "room_floor";
    private static final String CATEGORY = "category";
    private static final String URL = "url";
    private static final String ROOM_PRICE = "room_price";
    private static final String HOTEL_ID = "h_id";

    @Override
    public List<Room> resultSetToList(ResultSet rs) throws SQLException {
        List<Room> roomList = new ArrayList<>();

        while (rs.next()){
            Room room = Room.builder()
                    .id(rs.getInt(ID))
                    .room_floor(rs.getString(ROOM_FLOOR))
                    .category(rs.getString(CATEGORY))
                    .url(rs.getString(URL))
                    .room_price(rs.getInt(ROOM_PRICE))
                    .hotel_id(rs.getInt(HOTEL_ID))
                    .build();

            roomList.add(room);
        }

        return roomList;
    }

    @Override
    public Room resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Room.builder()
                    .id(rs.getInt(ID))
                    .room_floor(rs.getString(ROOM_FLOOR))
                    .category(rs.getString(CATEGORY))
                    .url(rs.getString(URL))
                    .room_price(rs.getInt(ROOM_PRICE))
                    .hotel_id(rs.getInt(HOTEL_ID))
                    .build();
        }

        return null;
    }
}
