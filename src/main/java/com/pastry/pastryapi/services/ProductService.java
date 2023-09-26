package com.pastry.pastryapi.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pastry.pastryapi.dto.Product.CreateProductDto;
import com.pastry.pastryapi.dto.Product.ProductDto;
import com.pastry.pastryapi.models.Category;
import com.pastry.pastryapi.models.Product;
import com.pastry.pastryapi.repository.CategoryRepository;
import com.pastry.pastryapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pastry.pastryapi.exceptions.ResourceNotFound;


import java.util.List;

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

    public ProductDto createProduct(CreateProductDto data) throws ResourceNotFound {
        Category categoryFounded = categoryRepository.findByCategoryName(data.getCategoryName());
        if (categoryFounded == null) throw new ResourceNotFound("CATEGORY", data.getCategoryName());;

        Product newProduct = mapper.convertValue(data, Product.class);
        newProduct.setCategoryId(categoryFounded.getCategoryId());
        newProduct = productRepository.save(newProduct);
        categoryFounded.getProducts().add(newProduct);
        categoryRepository.save(categoryFounded);

        return mapper.convertValue(newProduct, ProductDto.class);
    }

    public List<ProductDto> getAllProducts(String categoryFilter) {
        if (categoryFilter != null) {
            Category category = categoryRepository.findByCategoryName(categoryFilter);
            if (category == null) throw new ResourceNotFound("CATEGORY", categoryFilter);
            List<Product> products = productRepository.getProductByCategoryId(category.getCategoryId());
            return mapper.convertValue(products, new TypeReference<List<ProductDto>>() {
            });
        }
        return mapper.convertValue(productRepository.findAll(), new TypeReference<List<ProductDto>>() {
        });
    }

    public ProductDto findProductByName(String productName) {
        return null;
    }

    public ProductDto updateProduct(String productName) {
        return null;
    }

    public ProductDto deleteProduct(String productName) {
        return null;
    }
}
