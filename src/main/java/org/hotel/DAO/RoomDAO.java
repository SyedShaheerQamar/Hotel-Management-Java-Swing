package org.hotel.DAO;

import org.hotel.Domain.Hotel;
import org.hotel.Domain.Room;
import org.hotel.Mapper.RoomMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hotel.DAO.SqlQueryConstant.*;

public class RoomDAO extends BaseDAO implements iCrud<Room>{

    private final RoomMapper roomMapper = new RoomMapper();

    @Override
    public void insert(Room obj) {
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_ROOM);
            ps.setString(1, obj.getRoom_floor());
            ps.setString(2, obj.getCategory());
            ps.setString(3, obj.getUrl());
            ps.setInt(4, obj.getRoom_price());
            ps.setInt(5, obj.getHotel_id());
            ps.executeUpdate();

            System.out.println("Inserted!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_ROOM);

            return roomMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Room getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_ROOM_BY_ID);
            ps.setInt(1, id.intValue());

            ResultSet rs = ps.executeQuery();
            return roomMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Room obj, Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_ROOM);
            ps.setString(1, obj.getCategory());
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
            PreparedStatement ps = conn.prepareStatement(DELTE_ROOM_BY_ID);
            ps.setInt(1, id.intValue());
            ps.executeUpdate();

            System.out.println("Deleted!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateAllValues(Room obj, Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_ALL_VALUES_ROOM);
            ps.setString(1, obj.getRoom_floor());
            ps.setString(2, obj.getCategory());
            ps.setString(3, obj.getUrl());
            ps.setInt(4, obj.getRoom_price());
            ps.setInt(5, obj.getHotel_id());
            ps.setInt(6, id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Room> searchByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from room where category like '%"+name+"%';");

            ResultSet rs = ps.executeQuery();
            return roomMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Room> getAvailableRoom(Integer id, String adate, String ddate){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from room where h_id = "+id+" and id not in (select b.r_id from room r inner join booking b on r.id = b.r_id where arrival_date between '"+adate+"' and '"+ddate+"');"
            );

            ResultSet rs = ps.executeQuery();
            return roomMapper.resultSetToList(rs);

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
