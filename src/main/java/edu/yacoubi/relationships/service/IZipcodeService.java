package edu.yacoubi.relationships.service;

import edu.yacoubi.relationships.model.dto.request.ZipcodeRequestDto;
import edu.yacoubi.relationships.model.entity.Zipcode;

import java.util.List;

public interface IZipcodeService {
    Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto);

    List<Zipcode> getAllZipcodes();

    Zipcode getZipcode(Long zipcodeId);

    Zipcode updateZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto);

    void deleteZipcode(Long zipcodeId);

    Zipcode associateCityWithZipcode(Long zipcodeId, Long cityId);

    Zipcode disassociateCityFromZipcode(Long zipcodeId);
}
