package com.emazon.api_shopping_cart.infraestructure.output.mapper;

import com.emazon.api_shopping_cart.application.dto.stock.ArticleResponseDto;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import com.emazon.api_shopping_cart.infraestructure.output.entity.CartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICartEntityMapper {

    CartEntity cartToCartSaveEntity(CartSave cartSave);

    ArticleResponse articleResponseDtoToArticleResponse(ArticleResponseDto articleResponseDto);

    CartSave cartEntityToCartSave(CartEntity cartEntity);

}
