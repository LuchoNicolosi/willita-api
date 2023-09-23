package com.pastry.pastryapi.dto.Category;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class CategoryDto {
    private String categoryId;
    private String categoryName;
}
