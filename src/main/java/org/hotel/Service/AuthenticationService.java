package org.hotel.Service;

import org.hotel.DAO.BookingDAO;
import org.hotel.DAO.Hotel_AdminDAO;
import org.hotel.Domain.Hotel_Admin;

public class AuthenticationService {

    Hotel_AdminDAO hotelAdminDAO = new Hotel_AdminDAO();

    public Boolean getEmailAndPass(String email, String pass){
        Hotel_Admin hotelAdmin = hotelAdminDAO.getEmailAndPassword(email, pass);
        if(hotelAdmin != null){
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
