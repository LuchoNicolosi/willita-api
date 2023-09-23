package com.pastry.pastryapi.dto.Product;

import com.pastry.pastryapi.models.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {
    private String productId;
    private String productName;
    private Double price;
    private List<String> images;
    private String categoryId;
}
