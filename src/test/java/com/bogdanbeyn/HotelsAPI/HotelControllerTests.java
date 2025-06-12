package com.bogdanbeyn.HotelsAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@Sql("/data.sql")
public class HotelControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetHotels_thenReturns200AndHotelList() throws Exception {
        mockMvc.perform(get("/property-view/hotels"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].name", is("DoubleTree by Hilton Minsk")));
    }

    @Test
    void whenGetHotelById_thenReturns200AndCorrectHotel() throws Exception {
        mockMvc.perform(get("/property-view/hotels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("DoubleTree by Hilton Minsk")))
                .andExpect(jsonPath("$.address.city", is("Minsk")));
    }

    @Test
    void whenPostHotel_thenReturns201AndCreatedHotel() throws Exception {
        String newHotelJson = """
    {
        "name": "Minsk Marriott Hotel",
        "brand": "Marriott",
        "address": {
            "houseNumber": 20,
            "street": "Pobediteley Avenue",
            "city": "Minsk",
            "country": "Belarus",
            "postCode": "220020"
        },
        "contacts": {
            "phone": "+375 17 279-30-00",
            "email": "minsk.marriott@marriott.com"
        },
        "arrivalTime": {
            "checkIn": "15:00"
        }
    }
    """;

        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newHotelJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Minsk Marriott Hotel")));
    }

    @Test
    void whenSearchByCity_thenReturnsFilteredHotels() throws Exception {
        mockMvc.perform(get("/property-view/search?city=Minsk"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].address", containsString("Minsk")));
    }

    @Test
    void whenGetHistogramByBrand_thenReturnsCorrectCounts() throws Exception {
        mockMvc.perform(get("/property-view/histogram/brand"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Hilton", is(1)))
                .andExpect(jsonPath("$.Marriott", is(1)));
    }




}
