package org.hotel.Service;

import org.hotel.DAO.BookingDAO;
import org.hotel.DAO.CustomerDAO;
import org.hotel.DAO.HotelDAO;
import org.hotel.DAO.RoomDAO;
import org.hotel.Domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    BookingDAO dao = new BookingDAO();

    public String[][] getAvailableRoom(Integer id, String a_date, String d_Date){
        RoomDAO roomDAO = new RoomDAO();
        List<Room> roomList = roomDAO.getAvailableRoom(id, a_date, d_Date);

        return convertValuesIntoJTableForRoom(roomList, 6);
    }

    public String getTotalPrice(String adate, String ddate, Integer h_id){
        List<Bill> bookingList = dao.getTotalPrice(h_id);

        Integer price = bookingList.get(0).getPrice();

        return "The  total  bill  of  Hotel  ID  : "+h_id+"  is  "+price;
    }

    public String[][] MonthlyReportBooking(String adate, String ddate, Integer id){
        List<Booking> bookingList = dao.getMonthlyReportBooking(adate, ddate, id);

        return convertValuesIntoJTable(bookingList, 8);
    }

    public Boolean updateValueOfBooking(Integer id){
        Booking booking = dao.getById(Long.valueOf(id));

        if(booking.getBooking_status().equalsIgnoreCase("complete")){
            return Boolean.TRUE;
        }
        LocalDate now = LocalDate.now();

        booking.setBooking_status("complete");
        booking.setDeparture_date(String.valueOf(now));
        dao.updateAllValues(booking, id);
        return Boolean.FALSE;
    }

    public Boolean deleteValueOfBooking(Integer id){
        Booking booking = dao.getById(Long.valueOf(id));

        if(booking.getBooking_status().equalsIgnoreCase("inactive")){
            return Boolean.TRUE;
        }
        LocalDate now = LocalDate.now();

        booking.setBooking_status("inactive");
        booking.setDeparture_date(String.valueOf(now));
        dao.updateAllValues(booking, id);
        return Boolean.FALSE;
    }

    public Booking getBookingById(Integer id){

        return dao.getById(Long.valueOf(id));

    }

    public void deleteById(String id){

        dao.deleteById(Long.valueOf(id));

    }

    public void UpdateAllValuesOfBooking(Integer id,String hID, String rID, String cID, String price, String aDate, String dDate){
        Booking booking = Booking.builder()
                .hotel_id(Integer.valueOf(hID))
                .room_id(Integer.valueOf(rID))
                .customer_id(Integer.valueOf(cID))
                .price(Integer.valueOf(price))
                .arrival_date(aDate)
                .departure_date(dDate)
                .build();

        dao.updateAllValues(booking, id);
    }

    public String[][] searchByNameInBooking(String name){

        List<Booking> bookingList = dao.searchByName(name);
        return convertValuesIntoJTable(bookingList, 8);

    }

    public void insertIntoBooking(Integer hID, Integer rID, Integer cID, String price, String aDate, String dDate){
        Booking booking = Booking.builder()
                .hotel_id(hID)
                .room_id(rID)
                .customer_id(cID)
                .price(Integer.valueOf(price))
                .arrival_date(aDate)
                .departure_date(dDate)
                .booking_status("active")
                .build();

        dao.insert(booking);
    }

    public String[][] getAllValuesOfBooking(){

        List<Booking> bookingList = dao.getAllBooking();
        return convertValuesIntoJTable(bookingList, 8);
    }

    public String[] get_Values_Hotel(){
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> hotelList = hotelDAO.getAll();

        return convertValuesToComboBoxHotel(hotelList, 5);
    }

    public String[] get_Values_Customer(){
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customerList = customerDAO.getAll();

        return convertValuesToComboBoxCustomer(customerList, 5);
    }

    public String[] get_Values_Room(){
        RoomDAO roomDAO = new RoomDAO();
        List<Room> roomList = roomDAO.getAll();

        return convertValuesToComboBoxRoom(roomList, 5);
    }

    private String[][] convertValuesIntoJTable(List<Booking> bookingList, int columnSize) {

        String[][] data = new String[bookingList.size()][columnSize];

        for(int i=0; i<bookingList.size(); i++){
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getHotel_id());
            data[i][2] = String.valueOf(bookingList.get(i).getRoom_id());
            data[i][3] = String.valueOf(bookingList.get(i).getCustomer_id());
            data[i][4] = String.valueOf(bookingList.get(i).getPrice());
            data[i][5] = bookingList.get(i).getArrival_date();
            data[i][6] = bookingList.get(i).getDeparture_date();
            data[i][7] = bookingList.get(i).getBooking_status();
        }

        return data;
    }

    private String[][] convertValuesIntoJTableForRoom(List<Room> roomList, int columnSize) {

        String[][] data = new String[roomList.size()][columnSize];

        for(int i=0; i<roomList.size(); i++){
            data[i][0] = String.valueOf(roomList.get(i).getId());
            data[i][1] = roomList.get(i).getRoom_floor();
            data[i][2] = roomList.get(i).getCategory();
            data[i][3] = roomList.get(i).getUrl();
            data[i][4] = String.valueOf(roomList.get(i).getRoom_price());
            data[i][5] = String.valueOf(roomList.get(i).getHotel_id());
        }

        return data;
    }

    public String[] convertValuesToComboBoxHotel(List<Hotel> hotelList, Integer columnSize){

        String[][] data = new String[hotelList.size()][columnSize];
        String[] value = new String[hotelList.size()];

        for(int i=0; i<hotelList.size(); i++){
            data[i][0] = String.valueOf(hotelList.get(i).getId());
            data[i][1] = hotelList.get(i).getHotel_name();

            value[i] = data[i][0] + ",  "+ data[i][1];
        }

        return value;

    }

    public String[] convertValuesToComboBoxRoom(List<Room> roomList, Integer columnSize){

        String[][] data = new String[roomList.size()][columnSize];
        String[] value = new String[roomList.size()];

        for(int i=0; i<roomList.size(); i++){
            data[i][0] = String.valueOf(roomList.get(i).getId());
            data[i][1] = roomList.get(i).getRoom_floor();
            data[i][2] = roomList.get(i).getCategory();

            value[i] = data[i][0] + ",  "+ data[i][1] +",  "+ data[i][2];
        }

        return value;

    }

    public String[] convertValuesToComboBoxCustomer(List<Customer> customerList, Integer columnSize){

        String[][] data = new String[customerList.size()][columnSize];
        String[] value = new String[customerList.size()];

        for(int i=0; i<customerList.size(); i++){
            data[i][0] = String.valueOf(customerList.get(i).getId());
            data[i][1] = customerList.get(i).getName();

            value[i] = data[i][0] + ",  "+ data[i][1];
        }

        return value;

    }

}