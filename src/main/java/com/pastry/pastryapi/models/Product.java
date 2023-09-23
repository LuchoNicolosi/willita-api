package com.pastry.pastryapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document("Products")
public class Product {
    @Id
    private String productId;
    @Field(name = "product")
    private String productName;
    private Double price;
    private List<String> images;
    private String categoryId;


    public Product() {
    }
}
