package com.bogdanbeyn.HotelsAPI.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ArrivalTime{
    private String checkIn;
    private String checkOut;
}
