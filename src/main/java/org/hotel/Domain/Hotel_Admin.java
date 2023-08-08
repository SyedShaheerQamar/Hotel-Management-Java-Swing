package org.hotel.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Hotel_Admin {

    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String pass;

}
