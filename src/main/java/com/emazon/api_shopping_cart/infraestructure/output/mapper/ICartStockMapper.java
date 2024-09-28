package com.emazon.api_shopping_cart.infraestructure.output.mapper;

import com.emazon.api_shopping_cart.application.dto.stock.ArticlePriceResponseDto;
import com.emazon.api_shopping_cart.application.dto.stock.ArticleResponseDto;
import com.emazon.api_shopping_cart.domain.model.stock.ArticlePriceResponse;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICartStockMapper {


    ArticleResponse articleResponseDtoToArticleResponse(ArticleResponseDto articleResponseDto);

    List<ArticleResponse> articleResponseDtoListToArticleResponseList(List<ArticleResponseDto> articleResponseDto);

    List<ArticlePriceResponse> articlePriceResponseDtosToArticlePriceResponse
            (List<ArticlePriceResponseDto> articlePriceResponseDtos);

}
