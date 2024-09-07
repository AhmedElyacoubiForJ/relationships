package edu.yacoubi.relationships.service;

import edu.yacoubi.relationships.model.dto.request.CityRequestDto;
import edu.yacoubi.relationships.model.entity.City;

import java.util.List;

public interface ICityService {
    public City addCity(CityRequestDto cityRequestDto);
    public List<City> getCities();
    public City getCity(Long id);
    public City updateCity(Long id, CityRequestDto cityRequestDto);
    public void deleteCity(Long id);
}
