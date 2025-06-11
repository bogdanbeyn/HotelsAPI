package com.bogdanbeyn.HotelsAPI.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
