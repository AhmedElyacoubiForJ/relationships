package edu.yacoubi.relationships.service;

import edu.yacoubi.relationships.model.dto.request.CityRequestDto;
import edu.yacoubi.relationships.model.entity.City;

import java.util.List;

public interface ICityService {
    City addCity(CityRequestDto cityRequestDto);

    List<City> getCities();

    City getCity(Long id);

    City updateCity(Long id, CityRequestDto cityRequestDto);

    void deleteCity(Long id);
}
