package org.hotel.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Room {

    private Integer id;
    private String room_floor;
    private String category;
    private String url;
    private Integer room_price;
    private Integer hotel_id;
}
