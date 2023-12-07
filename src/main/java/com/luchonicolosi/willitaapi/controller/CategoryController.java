package com.luchonicolosi.willitaapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luchonicolosi.willitaapi.dto.Category.CategoryDto;
import com.luchonicolosi.willitaapi.dto.Category.CreateCategoryDto;
import com.luchonicolosi.willitaapi.dto.ResponseDto;
import com.luchonicolosi.willitaapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CategoryController {
    private final CategoryService categoryService;
    private final Logger LOGGER = Logger.getLogger(CategoryController.class);
    private final ObjectMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> result = mapper.convertValue(categoryService.getAllCategories(), new TypeReference<List<CategoryDto>>() {
        });
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDto<CategoryDto>> createCategory(@RequestBody CreateCategoryDto data) {
        CategoryDto res;
        try {
            res = mapper.convertValue(categoryService.createCategory(data), CategoryDto.class);
        } catch (Exception e) {
            LOGGER.error("Error creating category - " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDto<>("category successfully created", LocalDateTime.now(), res), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        try {
            CategoryDto result = mapper.convertValue(categoryService.getCategoryById(categoryId), CategoryDto.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error getting category - " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long categoryId, @RequestBody CreateCategoryDto data) {

        try {
            CategoryDto result = mapper.convertValue(categoryService.updateCategory(categoryId, data), CategoryDto.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error updating category - " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Something went wrong - " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
