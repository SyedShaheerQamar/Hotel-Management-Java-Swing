package org.hotel.DAO;

public class SqlQueryConstant {

    // hotel_admin
    public static final String GET_ALL_HOTEL_ADMIN =
            "select * from hotel_admin";

    public static final String GET_HOTEL_ADMIN_BY_ID =
            "select * from hotel_admin where id = ?";

    public static final String INSERT_INTO_HOTEL_ADMIN =
            "insert into hotel_admin (first_name, last_name, email, pass)" +
                    "values (?, ?, ?, ?)";

    public static final String UPDATE_HOTEL_ADMIN =
            "update hotel_admin set email = ? where id = ?";

    public static final String DELTE_HOTEL_ADMIN_BY_ID =
            "delete from hotel_admin where id = ?";

    public static final String GET_EMAIL_AND_PASS =
            "select * from hotel_admin where email = ? and pass = ?";

    public static final String UPDATE_ALL_VALUES_HOTEL_ADMIN =
            "update hotel_admin set first_name = ?, last_name = ?, email = ?, pass = ? where id = ?";

    // hotel
    public static final String INSERT_INTO_HOTEL =
            "insert into hotel (hotel_name, location, url, admin_id)" +
                    "values (?, ?, ?, ?)";

    public static final String GET_ALL_HOTEL =
            "select * from hotel";

    public static final String GET_HOTEL_BY_ID =
            "select * from hotel where id = ?";

    public static final String UPDATE_HOTEL =
            "update hotel_admin set location = ? where id = ?";

    public static final String DELTE_HOTEL_BY_ID =
            "delete from hotel where id = ?";

    // room
    public static final String INSERT_INTO_ROOM =
            "insert into room (room_floor, category, url, room_price, hid)" +
                    "values (?, ?, ?, ?, ?)";

    public static final String GET_ALL_ROOM =
            "select * from room";

    public static final String GET_ROOM_BY_ID =
            "select * from room where id = ?";

    public static final String UPDATE_ROOM =
            "update room set category = ? where id = ?";

    public static final String DELTE_ROOM_BY_ID =
            "delete from room where id = ?";

    // customer
    public static final String GET_ALL_CUSTOMER =
            "select * from customer";
    public static final String INSERT_CUSTOMER =
            "insert into customer (c_name, phone_number, cnic)" +
                    "values (?, ?, ?)";
    public static final String GET_CUSTOMER_BY_ID =
            "select * from customer where id = ?";

    public static final String UPDATE_CUSTOMER =
            "update customer set c_name = ? where id = ?";

    public static final String DELETE_CUSTOMER =
            "delete from customer where id = ?";

    // booking
    public static final String GET_ALL_BOOKING =
            "select * from booking";
    public static final String INSERT_INTO_BOOKING =
            "insert into booing (h_id, r_id, c_id, price, arrival_date, departure_date)" +
                    "values (?, ?, ?, ?, ?, ?)";
    public static final String GET_BOOKING_BY_ID =
            "select * from booking where id = ?";

    public static final String UPDATE_BOOKING =
            "update booking set price = ? where id = ?";

    public static final String DELETE_BOOKING =
            "delete from booking where id = ?";


}
