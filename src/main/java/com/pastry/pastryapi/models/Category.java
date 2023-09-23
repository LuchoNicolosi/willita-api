package com.pastry.pastryapi.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Categories")
@Getter
@Setter
public class Category {
    @Id
    private String categoryId;
    private String categoryName;
    @DBRef
    private List<Product> products;
    public Category() {
    }
}
