package com.bogdanbeyn.HotelsAPI.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address{
    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;

}
