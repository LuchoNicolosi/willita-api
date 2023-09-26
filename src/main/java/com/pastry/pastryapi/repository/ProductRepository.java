package com.pastry.pastryapi.repository;

import com.pastry.pastryapi.models.Category;
import com.pastry.pastryapi.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> getProductByCategoryId(String categoryId);
}
