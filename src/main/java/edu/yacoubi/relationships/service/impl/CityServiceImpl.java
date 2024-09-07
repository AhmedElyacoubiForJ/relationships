package edu.yacoubi.relationships.service.impl;

import edu.yacoubi.relationships.model.dto.request.CityRequestDto;
import edu.yacoubi.relationships.model.entity.City;
import edu.yacoubi.relationships.repository.CityRepository;
import edu.yacoubi.relationships.service.ICityService;
import jakarta.transaction.Transactional;
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
    public List<City> getAllCities() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public City getCity(Long cityId) {
        log.debug("Getting city with id: {}", cityId);
        return cityRepository.findById(cityId).orElseThrow(() ->
                new IllegalArgumentException("City not found with id: " + cityId)
        );
    }

    @Transactional
    @Override
    public City updateCity(Long cityId, CityRequestDto cityRequestDto) {
        return cityRepository.findById(cityId).map(city -> {
            city.setName(cityRequestDto.getName());
            return cityRepository.save(city);
        }).orElseThrow(() -> new IllegalArgumentException("City not found with id: " + cityId));
    }

    @Override
    public void deleteCity(Long cityId) {
        log.debug("Deleting city with id: {}", cityId);
        boolean existsById = cityRepository.existsById(cityId);
        if (!existsById) {
            throw new IllegalArgumentException("City not found with id: " + cityId);
        }
        cityRepository.deleteById(cityId);
    }
}
