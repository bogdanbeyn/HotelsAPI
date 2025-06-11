package com.bogdanbeyn.HotelsAPI.controller;

import com.bogdanbeyn.HotelsAPI.dto.HotelDTO;
import com.bogdanbeyn.HotelsAPI.entity.Hotel;
import com.bogdanbeyn.HotelsAPI.repository.HotelRepository;
import com.bogdanbeyn.HotelsAPI.service.HotelService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/property-view/hotels")
@AllArgsConstructor
public class HotelController {

    private HotelService hotelService;
    private HotelRepository hotelRepository;

    @GetMapping
    public List<HotelDTO> getHotels() {
        return hotelService.getHotelSummaries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.convertToSummary(savedHotel));
    }

    @GetMapping("/search")
    public List<HotelDTO> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) List<String> amenities) {
        return hotelService.searchHotels(name, brand, city, country, amenities);
    }

    @PostMapping("/{id}/amenities")
    public ResponseEntity<Hotel> addAmenities(
            @PathVariable Long id,
            @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
        return ResponseEntity.status(HttpStatus.OK).body(hotelRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Hotel with id" + id + "not found")));
    }



}
