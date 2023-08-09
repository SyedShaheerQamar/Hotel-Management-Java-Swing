package org.hotel.Mapper;

import org.hotel.Domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements iMapper<Customer> {

    private static final String ID = "ID";
    private static final String C_NAME = "c_name";
    private static final String PHONE_NUMBER = "phone_numebr";
    private static final String CNIC = "cnic";

    @Override
    public List<Customer> resultSetToList(ResultSet rs) throws SQLException {
        List<Customer> customers = new ArrayList<>();

        while (rs.next()){
            Customer cus = Customer.builder()
                    .id(rs.getInt(ID))
                    .name(rs.getString(C_NAME))
                    .phone(rs.getString(PHONE_NUMBER))
                    .cnic(rs.getString(CNIC))
                    .build();

            customers.add(cus);
        }

        return customers;
    }

    @Override
    public Customer resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Customer.builder()
                    .id(rs.getInt(ID))
                    .name(rs.getString(C_NAME))
                    .phone(rs.getString(PHONE_NUMBER))
                    .cnic(rs.getString(CNIC))
                    .build();
        }

        return null;
    }
}
