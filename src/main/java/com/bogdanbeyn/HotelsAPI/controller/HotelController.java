package com.bogdanbeyn.HotelsAPI.controller;

import com.bogdanbeyn.HotelsAPI.dto.HotelDTO;
import com.bogdanbeyn.HotelsAPI.entity.Hotel;
import com.bogdanbeyn.HotelsAPI.repository.HotelRepository;
import com.bogdanbeyn.HotelsAPI.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@Tag(name = "Отели", description = "API для управления отелями")
@RequestMapping("/property-view")
@AllArgsConstructor
public class HotelController {

    private HotelService hotelService;
    private HotelRepository hotelRepository;

    @Operation(summary = "Получить список отелей (краткая инфа)")
    @GetMapping("/hotels")
    public List<HotelDTO> getHotels() {
        return hotelService.getHotelSummaries();
    }

    @Operation(summary = "Получить полный инфо об отеле по ID")
    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Добавить отель")
    @PostMapping("/hotels")
    public ResponseEntity<HotelDTO> createHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.convertToSummary(savedHotel));
    }

    @Operation(summary = "Получение списка всех отелей с их краткой информацией по следующим параметрам: name, brand, city, country, amenities")
    @GetMapping("/search")
    public List<HotelDTO> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) List<String> amenities) {
        return hotelService.searchHotels(name, brand, city, country, amenities);
    }

    @Operation(summary = "Добавление списка amenities к отелю")
    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<Hotel> addAmenities(
            @PathVariable Long id,
            @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
        return ResponseEntity.status(HttpStatus.OK).body(hotelRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Hotel with id" + id + "not found")));
    }

    @Operation(summary = "Получение количества отелей сгруппированных по каждому значению указанного параметра. Параметр: brand, city, country, amenities.")
    @GetMapping("/histogram/{param}")
    public ResponseEntity<?> getHistogram(@PathVariable String param) {
        try {
            return ResponseEntity.ok(hotelService.getHistogram(param));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

}
