package com.pastry.pastryapi.controllers;

import com.pastry.pastryapi.dto.Category.CategoryDto;
import com.pastry.pastryapi.dto.Category.CreateCategoryDto;
import com.pastry.pastryapi.dto.ResponseDto;
import com.pastry.pastryapi.models.Category;
import com.pastry.pastryapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CategoryController {
    private final CategoryService categoryService;
    private final Logger LOGGER = Logger.getLogger(CategoryController.class);

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> result = categoryService.getAllCategories();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDto<CategoryDto>> createCategory(@RequestBody CreateCategoryDto data) {
        CategoryDto res;
        try {
            res = categoryService.createCategory(data);
        } catch (Exception e) {
            LOGGER.error("Error creating category - " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDto<>("category successfully created", HttpStatus.OK, LocalDateTime.now(), res), HttpStatus.OK);
    }
}
