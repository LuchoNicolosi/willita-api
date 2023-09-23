package com.pastry.pastryapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pastry.pastryapi.dto.Category.CategoryDto;
import com.pastry.pastryapi.dto.Category.CreateCategoryDto;
import com.pastry.pastryapi.dto.Product.CreateProductDto;
import com.pastry.pastryapi.dto.Product.ProductDto;
import com.pastry.pastryapi.models.Category;
import com.pastry.pastryapi.models.Product;
import com.pastry.pastryapi.repository.CategoryRepository;
import com.pastry.pastryapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ObjectMapper mapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ObjectMapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public ProductDto createProduct(CreateProductDto data) throws Exception {
        Category categoryFounded = categoryRepository.findByCategoryName(data.getCategoryName());
        if (categoryFounded == null) throw new Exception("Category not found");

        Product newProduct = mapper.convertValue(data, Product.class);
        newProduct.setCategoryId(categoryFounded.getCategoryId());
        newProduct = productRepository.save(newProduct);
        categoryFounded.getProducts().add(newProduct);
        categoryRepository.save(categoryFounded);

        return mapper.convertValue(newProduct, ProductDto.class);
    }
}
