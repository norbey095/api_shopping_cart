package com.emazon.api_shopping_cart.application.dto.cartdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleCartRequestDto {

    private Integer page;
    private Integer size;
    private boolean descending;
    private String categoryName;
    private String brandName;
}
