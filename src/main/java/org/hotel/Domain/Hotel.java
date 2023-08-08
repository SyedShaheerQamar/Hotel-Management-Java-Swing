package org.hotel.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Hotel {

    private Integer id;
    private String hotel_name;
    private String location;
    private String url;
    private Integer admin_id;
}
