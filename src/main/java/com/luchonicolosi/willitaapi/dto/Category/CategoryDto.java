package com.luchonicolosi.willitaapi.dto.Category;

import com.luchonicolosi.willitaapi.dto.Product.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class CategoryDto {
    private String categoryId;
    private String categoryName;
    private Set<ProductDto> products;
}
