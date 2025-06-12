package com.bogdanbeyn.HotelsAPI.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String brand;

    @Embedded
    private Address address;

    @Embedded
    private Contacts contacts;

    @Embedded
    private ArrivalTime arrivalTime;

    @ElementCollection
    private List<String> amenities;

//    public <E> Hotel(long l, String brand, String name, Address address, List<E> amenities, Contacts contacts) {
//        this.id = l;
//        this.name = name;
//        this.brand = brand;
//        this.address = address;
//        this.amenities = (List<String>) amenities;
//        this.contacts = contacts;
//    }
}

