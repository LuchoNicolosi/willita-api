package com.pastry.pastryapi.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pastry.pastryapi.dto.Category.CategoryDto;
import com.pastry.pastryapi.dto.Category.CreateCategoryDto;
import com.pastry.pastryapi.models.Category;
import com.pastry.pastryapi.models.Product;
import com.pastry.pastryapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ObjectMapper mapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ObjectMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public CategoryDto createCategory(CreateCategoryDto data) {
        Category category = mapper.convertValue(data, Category.class);

        List<Product> products = new ArrayList<>();
        category.setProducts(products);

        return mapper.convertValue(categoryRepository.save(category), CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories() {
        return mapper.convertValue(categoryRepository.findAll(), new TypeReference<List<CategoryDto>>() {
        });
    }

    public CategoryDto findCategoryByName(String categoryName) {
        return null;
    }

    public CategoryDto updateCategory(String categoryName) {
        return null;
    }

    public CategoryDto deleteCategory(String categoryName) {
        return null;
    }
}
