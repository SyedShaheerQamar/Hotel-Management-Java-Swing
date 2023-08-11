package org.hotel.Domain;


import lombok.*;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Booking {

    private Integer id;
    private Integer hotel_id;
    private Integer room_id;
    private Integer customer_id;
    private Integer price;
    private String arrival_date;
    private String departure_date;
    private String booking_status;

}
