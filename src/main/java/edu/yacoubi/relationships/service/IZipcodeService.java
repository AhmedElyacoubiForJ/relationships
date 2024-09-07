package edu.yacoubi.relationships.service;

import edu.yacoubi.relationships.model.dto.request.ZipcodeRequestDto;
import edu.yacoubi.relationships.model.entity.Zipcode;

import java.util.List;

public interface IZipcodeService {
    Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto);

    List<Zipcode> getZipcodes();

    Zipcode getZipcode(Long zipcodeId);

    Zipcode updateZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto);

    void deleteZipcode(Long zipcodeId);

    Zipcode addCityToZipcode(Long zipcodeId, Long cityId);

    Zipcode removeCityFromZipcode(Long zipcodeId);
}
