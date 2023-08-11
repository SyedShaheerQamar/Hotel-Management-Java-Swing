package org.hotel.DAO;

import org.hotel.Domain.Customer;
import org.hotel.Domain.Hotel;
import org.hotel.Mapper.CustomerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hotel.DAO.SqlQueryConstant.*;

public class CustomerDAO extends BaseDAO implements iCrud<Customer>{

    private final CustomerMapper customerMapper = new CustomerMapper();

    @Override
    public void insert(Customer obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getPhone());
            ps.setString(3, obj.getCnic());
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_CUSTOMER);
            return customerMapper.resultSetToList(rs);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_CUSTOMER_BY_ID);
            ps.setInt(1, id.intValue());

            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer obj, Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_CUSTOMER);
            ps.setString(1, obj.getName());
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
            PreparedStatement ps = conn.prepareStatement(DELETE_CUSTOMER);
            ps.setInt(1, id.intValue());
            ps.executeUpdate();

            System.out.println("Deleted!!!");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateAllValues(Customer obj, Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_ALL_VALUES_CUSTOMER);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getPhone());
            ps.setString(3, obj.getCnic());
            ps.setInt(4, id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Customer> searchByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from customer where c_name like '%"+name+"%';");

            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
