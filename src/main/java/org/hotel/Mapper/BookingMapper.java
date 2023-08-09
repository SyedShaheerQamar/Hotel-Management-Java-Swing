package org.hotel.Mapper;

import org.hotel.Domain.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapper implements iMapper<Booking>{

    private static final String ID = "id";
    private static final String HOTEL_ID = "h_id";
    private static final String ROOM_ID = "r_id";
    private static final String CUSTOMER_ID = "c_id";
    private static final String PRICE = "price";
    private static final String ARRIVAL_DATE = "arrival_date";
    private static final String DEPARTURE_DATE = "departure_date";

    @Override
    public List<Booking> resultSetToList(ResultSet rs) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();

        while (rs.next()){
            Booking book = Booking.builder()
                    .id(rs.getInt(ID))
                    .hotel_id(rs.getInt(HOTEL_ID))
                    .room_id(rs.getInt(ROOM_ID))
                    .customer_id(rs.getInt(CUSTOMER_ID))
                    .price(rs.getInt(PRICE))
                    .arrival_date(rs.getString(ARRIVAL_DATE))
                    .departure_date(rs.getString(DEPARTURE_DATE))
                    .build();

            bookingList.add(book);
        }

        return bookingList;
    }

    @Override
    public Booking resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Booking.builder()
                    .id(rs.getInt(ID))
                    .hotel_id(rs.getInt(HOTEL_ID))
                    .room_id(rs.getInt(ROOM_ID))
                    .customer_id(rs.getInt(CUSTOMER_ID))
                    .price(rs.getInt(PRICE))
                    .arrival_date(rs.getString(ARRIVAL_DATE))
                    .departure_date(rs.getString(DEPARTURE_DATE))
                    .build();
        }

        return null;
    }
}
