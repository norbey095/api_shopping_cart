package com.emazon.api_shopping_cart.application.dto.stock;

import lombok.Data;

import java.util.List;

@Data
public class ArticleResponseDto {

    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
    private BrandArticleResponseDto brand;
    private List<CategoryResponseListDto> categories;
}
