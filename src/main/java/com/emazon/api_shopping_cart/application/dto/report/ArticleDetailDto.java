package com.emazon.api_shopping_cart.application.dto.report;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleDetailDto {

    private Integer articleId;
    private String name;
    private double unitPrice;
    private Integer quantity;

}
