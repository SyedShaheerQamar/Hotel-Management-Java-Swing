package org.hotel.DAO;

public class SqlQueryConstant {

    public static final String GET_ALL_HOTEL_ADMIN =
            "select * from hotel_admin";

    public static final String GET_HOTEL_ADMIN_BY_ID =
            "select * from hotel_admin where id = ?";

    public static final String INSERT_INTO_HOTEL_ADMIN =
            "insert into hotel_admin (first_name, last_name, email, pass)" +
                    "values (?, ?, ?, ?)";

    public static final String UPDATE_HOTEL_ADMIN =
            "update hotel_admin set email = ? where id = ?";

    public static final String DELTE_HOTEL_BY_ID =
            "delete from hotel where id = ?";

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



}
