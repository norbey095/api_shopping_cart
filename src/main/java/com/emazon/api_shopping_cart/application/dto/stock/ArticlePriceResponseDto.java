package com.emazon.api_shopping_cart.application.dto.stock;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ArticlePriceResponseDto {

    private Integer id;
    private double price;
    private Integer quantity;
}
