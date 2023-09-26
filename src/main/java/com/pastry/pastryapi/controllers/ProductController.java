package com.pastry.pastryapi.controllers;

import com.pastry.pastryapi.dto.Category.CategoryDto;
import com.pastry.pastryapi.dto.Product.CreateProductDto;
import com.pastry.pastryapi.dto.Product.ProductDto;
import com.pastry.pastryapi.dto.ResponseDto;
import com.pastry.pastryapi.exceptions.ResourceNotFound;
import com.pastry.pastryapi.models.Product;
import com.pastry.pastryapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductController {
    private final ProductService productService;
    private final Logger LOGGER = Logger.getLogger(ProductController.class);

    @PostMapping
    public ResponseEntity<ResponseDto<ProductDto>> createProduct(@RequestBody CreateProductDto data) throws ResourceNotFound {
        ProductDto res = productService.createProduct(data);
        return new ResponseEntity<>(new ResponseDto<>("product successfully created", HttpStatus.OK, LocalDateTime.now(), res), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(name = "categoryFilter", required = false) String categoryFilter) {
        return new ResponseEntity<List<ProductDto>>(productService.getAllProducts(categoryFilter), HttpStatus.OK);
    }
}
