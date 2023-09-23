package com.pastry.pastryapi.dto.Product;


import lombok.Data;

import java.util.List;

@Data
public class CreateProductDto {
    private String categoryName;
    private String productName;
    private Double price;
    private List<String> images;
}
