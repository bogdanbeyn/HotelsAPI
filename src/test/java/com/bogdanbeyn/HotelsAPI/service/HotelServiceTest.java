package com.bogdanbeyn.HotelsAPI.service;

import com.bogdanbeyn.HotelsAPI.dto.HotelDTO;
import com.bogdanbeyn.HotelsAPI.entity.Address;
import com.bogdanbeyn.HotelsAPI.entity.ArrivalTime;
import com.bogdanbeyn.HotelsAPI.entity.Contacts;
import com.bogdanbeyn.HotelsAPI.entity.Hotel;
import com.bogdanbeyn.HotelsAPI.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Service
@SpringBootTest
public class HotelServiceTest {

    private HotelRepository hotelRepository = mock(HotelRepository.class);
    private HotelService hotelService = new HotelService(hotelRepository);

    @Test
    public void testGetHotels() {
        Hotel hotel = new Hotel(1L, "Hotel", "Cool hotel", "Belarus", new Address(1L,"Pushkin","Minsk","Belarus","21353"),new Contacts("+37432657823", "eigjweuhg@he.he"), new ArrivalTime("10:00","20:00"), List.of("Free Wi-Fi"));
        when(hotelRepository.findAll()).thenReturn(List.of(hotel));

        List<HotelDTO> result = hotelService.getHotelSummaries();
        assertFalse(result.isEmpty());
        assertEquals("Hotel", result.get(0).getName());
    }
}
