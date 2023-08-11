package org.hotel.Service;

import org.hotel.DAO.CustomerDAO;
import org.hotel.DAO.RoomDAO;
import org.hotel.Domain.Customer;
import org.hotel.Domain.Room;

import java.util.List;

public class CustomerService {

    CustomerDAO dao = new CustomerDAO();

    public Customer getCustomerById(Integer id){

        return dao.getById(Long.valueOf(id));

    }

    public void deleteById(String id){

        dao.deleteById(Long.valueOf(id));

    }

    public void UpdateAllValuesOfCustomer(Integer id,String cName, String phone, String cnic){
        Customer customer = Customer.builder()
                .name(cName)
                .phone(phone)
                .cnic(cnic)
                .build();

        dao.updateAllValues(customer, id);
    }

    public String[][] searchByNameInCustomer(String name){

        List<Customer> customerList = dao.searchByName(name);
        return convertValuesIntoJTable(customerList, 4);

    }

    public void insertIntoCustomer(String cName, String phone, String cnic){
        Customer customer = Customer.builder()
                .name(cName)
                .phone(phone)
                .cnic(cnic)
                .build();

        dao.insert(customer);
    }

    public String[][] getAllValuesOfCustomer(){

        List<Customer> customerList = dao.getAll();
        return convertValuesIntoJTable(customerList, 4);
    }

    private String[][] convertValuesIntoJTable(List<Customer> customerList, int columnSize) {

        String[][] data = new String[customerList.size()][columnSize];

        for(int i=0; i<customerList.size(); i++){
            data[i][0] = String.valueOf(customerList.get(i).getId());
            data[i][1] = customerList.get(i).getName();
            data[i][2] = customerList.get(i).getPhone();
            data[i][3] = customerList.get(i).getCnic();
        }

        return data;
    }
}
