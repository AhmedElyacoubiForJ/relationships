package edu.yacoubi.relationships.controller;

import edu.yacoubi.relationships.model.dto.request.CityRequestDto;
import edu.yacoubi.relationships.model.entity.City;
import edu.yacoubi.relationships.service.ICityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final ICityService cityService;

    @PostMapping()
    public ResponseEntity<City> addCity(@RequestBody final CityRequestDto cityRequestDto) {
        City addedCity = cityService.addCity(cityRequestDto);
        return new ResponseEntity<>(addedCity, HttpStatus.CREATED);
    }
    @Tag(name = "get", description = "GET methods of Book APIs")
    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable(name = "id") final Long id) {
        City existingCity = cityService.getCity(id);
        return new ResponseEntity<>(existingCity, HttpStatus.OK);
    }

    @Tag(name = "get", description = "GET methods of Book APIs")
    @GetMapping()
    public ResponseEntity<List<City>> getCities() {
        List<City> cities = cityService.getAllCities();
        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable(name = "id") final Long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(
            @PathVariable(name = "id") final Long id,
            @RequestBody final CityRequestDto cityRequestDto) {
        City updatedCity = cityService.updateCity(id, cityRequestDto);
        return new ResponseEntity<>(updatedCity, HttpStatus.OK);
    }
}
