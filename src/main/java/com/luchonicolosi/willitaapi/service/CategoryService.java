package com.luchonicolosi.willitaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luchonicolosi.willitaapi
.dto.Category.CreateCategoryDto;
import com.luchonicolosi.willitaapi
.exceptions.ResourceNotFound;
import com.luchonicolosi.willitaapi
.model.Category;
import com.luchonicolosi.willitaapi
.model.Product;
import com.luchonicolosi.willitaapi
.repository.CategoryRepository;
import com.luchonicolosi.willitaapi
.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    private final Logger LOGGER = Logger.getLogger(CategoryService.class);

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ObjectMapper mapper, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    public Category createCategory(CreateCategoryDto data) {
        Category category = mapper.convertValue(data, Category.class);

        Set<Product> products = new HashSet<>();
        category.setProducts(products);

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
//     return mapper.convertValue(categoryRepository.findAll(), new TypeReference<List<CategoryDto>>() {
//        });
    }

    public Category getCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new ResourceNotFound("Category", categoryId);
        }
        return category.get();
    }

    public Category updateCategory(Long categoryId, CreateCategoryDto data) {
        Category result = getCategoryById(categoryId);
        Category updateCategory = mapper.convertValue(data, Category.class);
        updateCategory.setId(result.getId());
        updateCategory.setProducts(result.getProducts());
        return categoryRepository.save(updateCategory);
    }

    public void deleteCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        categoryRepository.delete(category);
        for (Product product : category.getProducts()) {
            productRepository.delete(product);
        }
    }
}
