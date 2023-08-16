package org.hotel.DAO;

import org.hotel.Domain.Bill;
import org.hotel.Domain.Booking;
import org.hotel.Domain.Customer;
import org.hotel.Mapper.BookingMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hotel.DAO.SqlQueryConstant.*;

public class BookingDAO extends BaseDAO implements iCrud<Booking>{

    private final BookingMapper bookingMapper = new BookingMapper();

    @Override
    public void insert(Booking obj) {
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_BOOKING);
            ps.setInt(1, obj.getHotel_id());
            ps.setInt(2, obj.getRoom_id());
            ps.setInt(3, obj.getCustomer_id());
            ps.setInt(4, obj.getPrice());
            ps.setString(5, obj.getArrival_date());
            ps.setString(6, obj.getDeparture_date());
            ps.setString(7, obj.getBooking_status());
            ps.executeUpdate();

            System.out.println("Inserted!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_BOOKING);

            return bookingMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getAllBooking(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_ACTIVE_BOOKING);
            return bookingMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Booking getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_BOOKING_BY_ID);
            ps.setInt(1, id.intValue());

            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Booking obj, Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_BOOKING);
            ps.setString(1, obj.getBooking_status());
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
            PreparedStatement ps = conn.prepareStatement(DELETE_BOOKING);
            ps.setInt(1, id.intValue());
            ps.executeUpdate();

            System.out.println("Deleted!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateAllValues(Booking obj, Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_ALL_VALUES_BOOKING);
            ps.setInt(1, obj.getHotel_id());
            ps.setInt(2, obj.getRoom_id());
            ps.setInt(3, obj.getCustomer_id());
            ps.setInt(4, obj.getPrice());
            ps.setString(5, obj.getArrival_date());
            ps.setString(6, obj.getDeparture_date());
            ps.setString(7, obj.getBooking_status());
            ps.setInt(8, id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Booking> searchByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from booking where booking_status like '%"+name+"%';");

            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getMonthlyReportBooking(String adate, String ddate, Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from booking where arrival_date between '"+adate+"' and '"+ddate+"' and h_id = "+id+";");

            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Bill> getTotalPrice(Integer id, String adate, String ddate){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select sum(b.price*datediff(departure_date, arrival_date)) as total_bill from booking b where b.arrival_date between '"+adate+"' and '"+ddate+"' and b.departure_date between '"+adate+"' and '"+ddate+"' and h_id = "+id+";"
            );

            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetToInteger(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Booking getAllNames(Integer hid, Integer cid, Integer rid){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_ALL_NAMES);
            ps.setInt(1, hid);
            ps.setInt(2, cid);
            ps.setInt(3, rid);

            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}