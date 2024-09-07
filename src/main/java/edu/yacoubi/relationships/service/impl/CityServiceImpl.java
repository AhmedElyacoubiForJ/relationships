package edu.yacoubi.relationships.service.impl;

import edu.yacoubi.relationships.model.dto.request.CityRequestDto;
import edu.yacoubi.relationships.model.entity.City;
import edu.yacoubi.relationships.repository.CityRepository;
import edu.yacoubi.relationships.service.ICityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements ICityService {
    private final CityRepository cityRepository;

    @Override
    public City addCity(CityRequestDto cityRequestDto) {
        return cityRepository.save(new City(cityRequestDto.getName()));
    }

    @Override
    public List<City> getCities() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public City getCity(Long id) {
        log.debug("Getting city with id: {}", id);
        return cityRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("City not found with id: " + id)
        );
    }

    @Override
    public City updateCity(Long id, CityRequestDto cityRequestDto) {
        return cityRepository.findById(id).map(city -> {
            city.setName(cityRequestDto.getName());
            return cityRepository.save(city);
        }).orElseThrow(() -> new IllegalArgumentException("City not found with id: " + id));
    }

    @Override
    public void deleteCity(Long id) {
        log.debug("Deleting city with id: {}", id);
        boolean existsById = cityRepository.existsById(id);
        if (!existsById) {
            throw new IllegalArgumentException("City not found with id: " + id);
        }
        cityRepository.deleteById(id);
    }
}
