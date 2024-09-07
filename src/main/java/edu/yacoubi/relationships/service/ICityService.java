package edu.yacoubi.relationships.service;

import edu.yacoubi.relationships.model.dto.request.CityRequestDto;
import edu.yacoubi.relationships.model.entity.City;

import java.util.List;

public interface ICityService {
    City addCity(CityRequestDto cityRequestDto);

    List<City> getAllCities();

    City getCity(Long cityId);

    City updateCity(Long cityId, CityRequestDto cityRequestDto);

    void deleteCity(Long cityId);
}
