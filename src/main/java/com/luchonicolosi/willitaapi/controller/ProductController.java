package com.luchonicolosi.willitaapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luchonicolosi.willitaapi
.dto.Product.CreateProductDto;
import com.luchonicolosi.willitaapi
.dto.Product.ProductDto;
import com.luchonicolosi.willitaapi
.dto.ResponseDto;
import com.luchonicolosi.willitaapi
.exceptions.ResourceNotFound;
import com.luchonicolosi.willitaapi
.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductController {
    private final ProductService productService;
    private final Logger LOGGER = Logger.getLogger(ProductController.class);
    private final ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseDto<ProductDto>> createProduct(@RequestBody CreateProductDto data) throws ResourceNotFound {
        ProductDto res = mapper.convertValue(productService.createProduct(data), ProductDto.class);
        return new ResponseEntity<>(new ResponseDto<>("product successfully created", LocalDateTime.now(), res), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(name = "categoryFilter", required = false) String categoryFilter) {
        return new ResponseEntity<List<ProductDto>>(mapper.convertValue(productService.getAllProducts(categoryFilter), new TypeReference<List<ProductDto>>() {}), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ProductDto findProductById(@PathVariable Long productId) {
        return null;
    }

    @PutMapping("/{categoryId}")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody CreateProductDto data) {
        return null;
    }

    @DeleteMapping("/{categoryId}")
    public ProductDto deleteProduct(@PathVariable Long productId) {
        return null;
    }
}
