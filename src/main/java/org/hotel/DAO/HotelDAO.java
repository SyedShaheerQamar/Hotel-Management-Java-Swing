package org.hotel.DAO;

import org.hotel.Domain.Hotel;
import org.hotel.Mapper.Hotel_Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hotel.DAO.SqlQueryConstant.*;

public class HotelDAO extends BaseDAO implements iCrud<Hotel> {

    private final Hotel_Mapper hotelMapper = new Hotel_Mapper();

    @Override
    public void insert(Hotel obj) {
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_HOTEL);
            ps.setString(1, obj.getHotel_name());
            ps.setString(2, obj.getLocation());
            ps.setString(3, obj.getUrl());
            ps.setInt(4, obj.getAdmin_id());
            ps.executeUpdate();

            System.out.println("Inserted!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Hotel> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_HOTEL);

            return hotelMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hotel getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_HOTEL_ADMIN_BY_ID);
            ps.setInt(1, id.intValue());

            ResultSet rs = ps.executeQuery();
            return hotelMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Hotel obj, Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_HOTEL_ADMIN);
            ps.setString(1, obj.getLocation());
            ps.setInt(2, id.intValue());
            ps.executeUpdate();

            System.out.println("Updated!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(DELTE_HOTEL_BY_ID);
            ps.setInt(1, id.intValue());
            ps.executeUpdate();

            System.out.println("Deleted!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
