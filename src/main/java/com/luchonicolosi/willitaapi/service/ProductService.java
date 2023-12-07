package com.luchonicolosi.willitaapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luchonicolosi.willitaapi
.dto.Product.CreateProductDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Product createProduct(CreateProductDto data) throws ResourceNotFound {
        Category categoryFounded = categoryRepository.findByName(data.getCategoryName());
        if (categoryFounded == null) throw new ResourceNotFound("CATEGORY", null);

        Product newProduct = mapper.convertValue(data, Product.class);
        newProduct.setCategory(categoryFounded);
        newProduct = productRepository.save(newProduct);
        categoryFounded.getProducts().add(newProduct);
        categoryRepository.save(categoryFounded);

        return newProduct;
    }

    public List<Product> getAllProducts(String categoryFilter) {
        if (categoryFilter != null) {
            Category category = categoryRepository.findByName(categoryFilter);
            if (category == null) throw new ResourceNotFound("CATEGORY", null);
            return productRepository.getProductByCategoryId(category.getId());
        }
        return productRepository.findAll();
    }

    public Product findProductById(String productId) {
        return null;
    }

    public Product updateProduct(String productName) {
        return null;
    }

    public Product deleteProduct(String productName) {
        return null;
    }
}
