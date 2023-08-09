package org.hotel.Mapper;

import org.hotel.Domain.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Hotel_Mapper implements iMapper<Hotel>{

    private static final String HOTEL_NAME = "hotel_name";
    private static final String LOCATION = "location";
    private static final String URL = "url";
    private static final String HOTEL_ADMIN_ID = "admin_id";

    @Override
    public List<Hotel> resultSetToList(ResultSet rs) throws SQLException {
        List<Hotel> hotelList = new ArrayList<>();

        while (rs.next()){
            Hotel hotel = Hotel.builder()
                    .hotel_name(rs.getString(HOTEL_NAME))
                    .location(rs.getString(LOCATION))
                    .url(rs.getString(URL))
                    .admin_id(rs.getInt(HOTEL_ADMIN_ID))
                    .build();

            hotelList.add(hotel);
        }

        return hotelList;
    }

    @Override
    public Hotel resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Hotel.builder()
                    .hotel_name(rs.getString(HOTEL_NAME))
                    .location(rs.getString(LOCATION))
                    .url(rs.getString(URL))
                    .admin_id(rs.getInt(HOTEL_ADMIN_ID))
                    .build();
        }

        return null;
    }
}
