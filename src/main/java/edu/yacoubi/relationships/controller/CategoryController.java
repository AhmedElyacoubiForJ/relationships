package edu.yacoubi.relationships.controller;

import edu.yacoubi.relationships.model.dto.request.CategoryRequestDto;
import edu.yacoubi.relationships.model.dto.response.CategoryResponseDto;
import edu.yacoubi.relationships.service.ICategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;


    // Restful API methods for category management...
    @PostMapping()
    public ResponseEntity<CategoryResponseDto> addCategory(
            @RequestBody final CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.addCategory(categoryRequestDto), HttpStatus.CREATED);
    }

    @Tag(name = "get", description = "GET methods of Book APIs")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable final Long id) {
        CategoryResponseDto category = categoryService.getCategoryDetails(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Tag(name = "get", description = "GET methods of Book APIs")
    @GetMapping()
    public ResponseEntity<Iterable<CategoryResponseDto>> getCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable final Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable final Long id,
            @RequestBody final CategoryRequestDto categoryData) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryData), HttpStatus.OK);
    }
}
