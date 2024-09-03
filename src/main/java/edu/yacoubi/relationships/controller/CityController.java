package edu.yacoubi.relationships.controller;

import edu.yacoubi.relationships.service.ICityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final ICityService cityService;
}
