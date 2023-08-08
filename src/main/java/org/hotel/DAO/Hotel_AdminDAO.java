package org.hotel.DAO;

import org.hotel.Domain.Hotel_Admin;
import org.hotel.Mapper.Hotel_Admin_Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import static org.hotel.DAO.SqlQueryConstant.*;

public class Hotel_AdminDAO extends BaseDAO implements iCurd<Hotel_Admin>{

    private static final Hotel_Admin_Mapper hotel_admin_mapper = new Hotel_Admin_Mapper();

    @Override
    public void insert(Hotel_Admin obj) {
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_HOTEL_ADMIN);
            ps.setString(1, obj.getFirst_name());
            ps.setString(2, obj.getLast_name());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPass());
            ps.executeUpdate();

            System.out.println("Inserted!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Hotel_Admin> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_HOTEL_ADMIN);

            return hotel_admin_mapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hotel_Admin getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_HOTEL_ADMIN_BY_ID);
            ps.setInt(1, id.intValue());

            ResultSet rs = ps.executeQuery();
            return hotel_admin_mapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Hotel_Admin obj, Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_HOTEL_ADMIN);
            ps.setString(1, obj.getEmail());
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
            PreparedStatement ps = conn.prepareStatement(DELTE_HOTEL_ADMIN_BY_ID);
            ps.setInt(1, id.intValue());
            ps.executeUpdate();

            System.out.println("Deleted!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
