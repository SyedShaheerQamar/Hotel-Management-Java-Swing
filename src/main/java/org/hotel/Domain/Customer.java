package org.hotel.Domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customer {

    private Integer id;
    private String name;
    private String phone;
    private String cnic;
}
