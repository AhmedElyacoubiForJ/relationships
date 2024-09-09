package edu.yacoubi.relationships.controller;

import edu.yacoubi.relationships.model.dto.request.ZipcodeRequestDto;
import edu.yacoubi.relationships.model.entity.Zipcode;
import edu.yacoubi.relationships.service.IZipcodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name = "get zipcode", description = "GET methods to Zipcode Endpoint")
    @GetMapping("/{id}")
    public ResponseEntity<Zipcode> getZipcode(@PathVariable(name = "id") final Long id) {
        Zipcode exitingCity = zipcodeService.getZipcode(id);
        return new ResponseEntity<>(exitingCity, HttpStatus.OK);
    }

    @Tag(name = "get zipcode", description = "GET methods to Zipcode Endpoint")
    @GetMapping()
    public ResponseEntity<List<Zipcode>> getZipcodes() {
        List<Zipcode> zipcodes = zipcodeService.getAllZipcodes();
        return new ResponseEntity<>(zipcodes, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a zipcode",
            description = "Delete a Zipcode from the database."
    )
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", content = {
//                    @Content(mediaType = "application/json",
//                    schema = @Schema(implementation = Zipcode.class))
//            }),
//            @ApiResponse(responseCode = "404", description = "Zipcode not found",
//                    content = @Content) }
//    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZipcode(@PathVariable(name = "id") final Long id) {
        zipcodeService.deleteZipcode(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Update a zipcode",
            description = "Update an existing zipcode. The response is updated Zipcode object."
    )
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
