package com.bogdanbeyn.HotelsAPI.controller;

import com.bogdanbeyn.HotelsAPI.dto.HotelDTO;
import com.bogdanbeyn.HotelsAPI.repository.HotelRepository;
import com.bogdanbeyn.HotelsAPI.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@WebMvcTest(HotelController.class)
@ContextConfiguration(classes = {HotelController.class, HotelService.class})
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @MockBean
    private HotelRepository hotelRepository;

    @Test
    public void shouldReturnHotels() throws Exception {
        List<HotelDTO> hotels = List.of(new HotelDTO(1L, "Hotel", "Cool hotel", "9 Pushiness", "+3753925325"));

        when(hotelService.getHotelSummaries()).thenReturn(hotels);

        mockMvc.perform(get("/property-view/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Hotel"));
    }
}
