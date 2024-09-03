package edu.yacoubi.relationships.controller;

import edu.yacoubi.relationships.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final IAuthorService authorService;
}
