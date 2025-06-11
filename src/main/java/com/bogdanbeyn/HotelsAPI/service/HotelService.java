package com.bogdanbeyn.HotelsAPI.service;

import com.bogdanbeyn.HotelsAPI.dto.HotelDTO;
import com.bogdanbeyn.HotelsAPI.entity.Hotel;
import com.bogdanbeyn.HotelsAPI.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<HotelDTO> getHotelSummaries() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map(this::convertToSummary).collect(Collectors.toList());
    }

    public List<HotelDTO> searchHotels(String name, String brand, String city, String country, List<String> amenities) {
        return hotelRepository.searchHotels(
                name != null ? name.toLowerCase() : null,
                brand != null ? brand.toLowerCase() : null,
                city != null ? city.toLowerCase() : null,
                country != null ? country.toLowerCase() : null,
                amenities != null ? amenities.stream().map(String::toLowerCase).toList() : null
        ).stream().map(this::convertToSummary).collect(Collectors.toList());
    }

    public HotelDTO addAmenities(Long id, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with ID: " + id));

        hotel.getAmenities().addAll(amenities);
        hotelRepository.save(hotel);

        return convertToSummary(hotel);
    }


    public Map<String, Long> getHistogram(String param) {
        List<Object[]> result = switch (param.toLowerCase()) {
            case "brand" -> hotelRepository.getHotelsByBrand();
            case "city" -> hotelRepository.getHotelsByCity();
            case "country" -> hotelRepository.getHotelsByCountry();
            case "amenities" -> hotelRepository.getHotelsByAmenities();
            default -> throw new IllegalArgumentException("Unsupported parameter: " + param);
        };

        return result.stream().collect(Collectors.toMap(r -> (String) r[0], r -> (Long) r[1]));
    }


    public HotelDTO convertToSummary(Hotel hotel) {
        String fullAddress = String.format("%s %s, %s, %s, %s",
                hotel.getAddress().getHouseNumber(),
                hotel.getAddress().getStreet(),
                hotel.getAddress().getCity(),
                hotel.getAddress().getCountry(),
                hotel.getAddress().getPostCode());

        return new HotelDTO(hotel.getId(), hotel.getName(), hotel.getDescription(), fullAddress, hotel.getContacts().getPhone());
    }


}




