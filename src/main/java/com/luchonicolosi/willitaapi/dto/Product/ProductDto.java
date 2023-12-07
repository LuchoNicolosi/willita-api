package com.luchonicolosi.willitaapi.dto.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private Double price;
    private List<String> images;
}
