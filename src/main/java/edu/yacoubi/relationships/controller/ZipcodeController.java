package edu.yacoubi.relationships.controller;

import edu.yacoubi.relationships.model.dto.request.ZipcodeRequestDto;
import edu.yacoubi.relationships.model.entity.City;
import edu.yacoubi.relationships.model.entity.Zipcode;
import edu.yacoubi.relationships.service.IZipcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcodes")
@RequiredArgsConstructor
public class ZipcodeController {
    private final IZipcodeService zipcodeService;

    @PostMapping()
    public ResponseEntity<Zipcode> addZipcode(ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode addedZipcode = zipcodeService.addZipcode(zipcodeRequestDto);
        return new ResponseEntity<>(addedZipcode, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zipcode> getZipcode(@PathVariable(name = "id") final Long id) {
        Zipcode exitingCity = zipcodeService.getZipcode(id);
        return new ResponseEntity<>(exitingCity, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Zipcode>> getZipcodes() {
        List<Zipcode> zipcodes = zipcodeService.getAllZipcodes();
        return new ResponseEntity<>(zipcodes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZipcode(@PathVariable(name = "id") final Long id) {
        zipcodeService.deleteZipcode(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zipcode> updateZipcode(
            @PathVariable(name = "id") final Long id,
            @RequestBody final ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode updatedZipcode = zipcodeService.updateZipcode(id, zipcodeRequestDto);
        return new ResponseEntity<>(updatedZipcode, HttpStatus.OK);
    }

    @PutMapping("/associate/{zipcodeId}/city/{cityId}")
    public ResponseEntity<Zipcode> associateCityWithZipcode(
            @PathVariable(name = "zipcodeId") final Long zipcodeId,
            @PathVariable(name = "cityId") final Long cityId) {
        Zipcode associatedZipcode = zipcodeService.associateCityWithZipcode(zipcodeId, cityId);
        return new ResponseEntity<>(associatedZipcode, HttpStatus.OK);
    }

    @PutMapping("/disassociate/{zipcodeId}")
    public ResponseEntity<Zipcode> disassociateCityFromZipcode(
            @PathVariable(name = "zipcodeId") final Long zipcodeId) {
        Zipcode disassociatedZipcode = zipcodeService.disassociateCityFromZipcode(zipcodeId);
        return new ResponseEntity<>(disassociatedZipcode, HttpStatus.OK);
    }
}
