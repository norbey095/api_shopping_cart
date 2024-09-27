package com.emazon.api_shopping_cart.application.dto.cart;

import com.emazon.api_shopping_cart.application.util.ConstantsDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CartRequestDto {
    @NotNull(message = ConstantsDto.ID_ARTICLE_REQUIRED)
    private Integer idArticle;
    @NotNull(message = ConstantsDto.QUANTITY_REQUIRED)
    @Min(value = ConstantsDto.NUMBER_0, message = ConstantsDto.QUANTITY_NOT_NEGATIVE)
    private Integer quantity;
}
