package edu.yacoubi.relationships.controller;

import edu.yacoubi.relationships.model.dto.request.CityRequestDto;
import edu.yacoubi.relationships.model.entity.City;
import edu.yacoubi.relationships.service.ICityService;
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
        return new ResponseEntity<>(cityService.addCity(cityRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable(name = "id") final Long id) {
        City city = cityService.getCity(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<City>> getCities() {
        List<City> cities = cityService.getAllCities();
        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable(name = "id") final Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<City> updateCity(
            @PathVariable(name = "id") final Long id,
            @RequestBody final CityRequestDto cityRequestDto) {
        return new ResponseEntity<>(cityService.updateCity(id, cityRequestDto), HttpStatus.OK);
    }
}
