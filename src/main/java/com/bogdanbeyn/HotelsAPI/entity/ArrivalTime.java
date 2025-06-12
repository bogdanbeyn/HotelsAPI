package com.bogdanbeyn.HotelsAPI.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class ArrivalTime{
    private String checkIn;
    private String checkOut;
}
