package edu.yacoubi.relationships.controller;

import edu.yacoubi.relationships.service.IZipcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zipcode")
@RequiredArgsConstructor
public class ZipcodeController {
    private final IZipcodeService zipcodeService;
}
