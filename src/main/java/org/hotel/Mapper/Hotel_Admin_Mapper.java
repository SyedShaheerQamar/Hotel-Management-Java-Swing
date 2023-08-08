package org.hotel.Mapper;

import org.hotel.Domain.Hotel_Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Hotel_Admin_Mapper implements iMapper<Hotel_Admin>{

    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "pass";


    @Override
    public List<Hotel_Admin> resultSetToList(ResultSet rs) throws SQLException {
        List<Hotel_Admin> hotel_adminList = new ArrayList<>();

        while(rs.next()){
            Hotel_Admin ha = Hotel_Admin.builder()
                    .id(rs.getInt(ID))
                    .first_name(rs.getString(FIRST_NAME))
                    .last_name(rs.getString(LAST_NAME))
                    .email(rs.getString(EMAIL))
                    .pass(rs.getString(PASSWORD))
                    .build();

            hotel_adminList.add(ha);
        }
        return hotel_adminList;
    }

    @Override
    public Hotel_Admin resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Hotel_Admin.builder()
                    .id(rs.getInt(ID))
                    .first_name(rs.getString(FIRST_NAME))
                    .last_name(rs.getString(LAST_NAME))
                    .email(rs.getString(EMAIL))
                    .pass(rs.getString(PASSWORD))
                    .build();
        }
        return null;
    }
}
