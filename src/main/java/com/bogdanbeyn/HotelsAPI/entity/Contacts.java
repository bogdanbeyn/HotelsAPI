package com.bogdanbeyn.HotelsAPI.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Contacts{
    private String phone;
    private String email;
}
