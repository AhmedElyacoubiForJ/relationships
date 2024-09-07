package edu.yacoubi.relationships.service.impl;

import edu.yacoubi.relationships.model.dto.request.ZipcodeRequestDto;
import edu.yacoubi.relationships.model.entity.City;
import edu.yacoubi.relationships.model.entity.Zipcode;
import edu.yacoubi.relationships.repository.ZipcodeRepository;
import edu.yacoubi.relationships.service.ICityService;
import edu.yacoubi.relationships.service.IZipcodeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class ZipcodeServiceImpl implements IZipcodeService {
    private final ZipcodeRepository zipcodeRepository;
    private final ICityService cityService;

    @Override
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeData) {
        log.info("Adding zipcode: {}", zipcodeData);
        Zipcode newZipcode = new Zipcode();
        newZipcode.setName(zipcodeData.getName());
        if (zipcodeData.getCityId() == null) {
            return zipcodeRepository.save(newZipcode);
        }
        City selectedCity = cityService.getCity(zipcodeData.getCityId());
        newZipcode.setCity(selectedCity);
        return zipcodeRepository.save(newZipcode);
    }

    @Override
    public List<Zipcode> getAllZipcodes() {
        return StreamSupport.stream(
                zipcodeRepository.findAll().spliterator(),
                false
        ).collect(Collectors.toList());
    }

    @Override
    public Zipcode getZipcode(Long zipcodeId) {
        log.debug("Getting zipcode with id: {}", zipcodeId);
        return zipcodeRepository.findById(zipcodeId).orElseThrow(() ->
                new IllegalArgumentException("Zipcode not found with id: " + zipcodeId)
        );
    }

    @Transactional
    @Override
    public Zipcode updateZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeData) {
        log.info("Updating zipcode with id: {} with data: {}", zipcodeId, zipcodeData);
        Zipcode existingZipcode = getZipcode(zipcodeId);
        existingZipcode.setName(zipcodeData.getName());
        if (zipcodeData.getCityId() != null) {
            City selectedCity = cityService.getCity(zipcodeData.getCityId());
            existingZipcode.setCity(selectedCity);
        }
        return existingZipcode;
    }

    @Override
    public void deleteZipcode(Long zipcodeId) {
        log.debug("Deleting zipcode with id: {}", zipcodeId);
        boolean existsById = zipcodeRepository.existsById(zipcodeId);
        if (!existsById) {
            throw new IllegalArgumentException("Zipcode not found with id: " + zipcodeId);
        }
        zipcodeRepository.deleteById(zipcodeId);
    }

    @Transactional
    @Override
    public Zipcode associateCityWithZipcode(Long zipcodeId, Long cityId) {
        Zipcode existingZipcode = getZipcode(zipcodeId);
        if (Objects.nonNull(existingZipcode.getCity())) {
            throw new IllegalArgumentException("Zipcode already has a city associated with it.");
        }
        City existingCity = cityService.getCity(cityId);
        existingZipcode.setCity(existingCity);
        return existingZipcode;
    }

    @Transactional
    @Override
    public Zipcode disassociateCityFromZipcode(Long zipcodeId) {
        Zipcode existingZipcode = getZipcode(zipcodeId);
        if (Objects.isNull(existingZipcode.getCity())) {
            throw new IllegalArgumentException("Zipcode does not have a city associated with it.");
        }
        existingZipcode.setCity(null);
        return existingZipcode;
    }
}
