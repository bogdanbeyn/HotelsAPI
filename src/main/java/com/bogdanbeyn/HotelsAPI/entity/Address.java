package com.bogdanbeyn.HotelsAPI.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class Address{
    private Long houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;

}
