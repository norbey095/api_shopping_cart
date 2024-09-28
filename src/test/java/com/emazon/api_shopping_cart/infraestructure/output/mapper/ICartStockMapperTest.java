package com.emazon.api_shopping_cart.infraestructure.output.mapper;

import com.emazon.api_shopping_cart.application.dto.stock.ArticlePriceResponseDto;
import com.emazon.api_shopping_cart.application.dto.stock.ArticleResponseDto;
import com.emazon.api_shopping_cart.application.dto.stock.BrandArticleResponseDto;
import com.emazon.api_shopping_cart.domain.model.stock.ArticlePriceResponse;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ICartStockMapperTest {

    private final ICartStockMapper cartEntityMapper = Mappers.getMapper(ICartStockMapper.class);

    @Test
    void testArticleResponseDtoToArticleResponse() {
        ArticleResponseDto articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setId(ConstantsInfTest.ID);
        articleResponseDto.setName(ConstantsInfTest.NAME);
        articleResponseDto.setDescription(ConstantsInfTest.DESCRIPTION);
        articleResponseDto.setQuantity(ConstantsInfTest.QUANTITY);
        articleResponseDto.setPrice(ConstantsInfTest.PRICE);
        articleResponseDto.setBrand(new BrandArticleResponseDto());
        articleResponseDto.setCategories(new ArrayList<>());


        ArticleResponse articleResponse = cartEntityMapper.articleResponseDtoToArticleResponse(articleResponseDto);


        assertNotNull(articleResponse);
        assertEquals(articleResponse.getId(), articleResponseDto.getId());
        assertEquals(articleResponse.getName(), articleResponseDto.getName());
        assertEquals(articleResponse.getDescription(), articleResponseDto.getDescription());
        assertEquals(articleResponse.getQuantity(), articleResponseDto.getQuantity());
        assertEquals(articleResponse.getPrice(), articleResponseDto.getPrice());
    }

    @Test
    void testArticleResponseDtoToArticleResponseNull() {
        ArticleResponse articleResponse = cartEntityMapper.articleResponseDtoToArticleResponse(null);

        assertNull(articleResponse);
    }

    @Test
    void testArticleResponseDtoToArticleResponseList() {
        ArticleResponseDto articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setId(ConstantsInfTest.ID);
        articleResponseDto.setName(ConstantsInfTest.NAME);
        articleResponseDto.setDescription(ConstantsInfTest.DESCRIPTION);
        articleResponseDto.setQuantity(ConstantsInfTest.QUANTITY);
        articleResponseDto.setPrice(ConstantsInfTest.PRICE);
        articleResponseDto.setBrand(new BrandArticleResponseDto());
        articleResponseDto.setCategories(new ArrayList<>());

        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();
        articleResponseDtoList.add(articleResponseDto);


        List<ArticleResponse> cartSave = cartEntityMapper
                .articleResponseDtoListToArticleResponseList(articleResponseDtoList);


        Assertions.assertNotNull(cartSave);
    }

    @Test
    void testArticleResponseDtoToArticleResponseListNull() {
        List<ArticleResponse> articleResponse = cartEntityMapper.articleResponseDtoListToArticleResponseList(null);

        assertNull(articleResponse);
    }

    @Test
    void testArticlePriceResponseDtosToArticlePriceResponse() {
        ArticlePriceResponseDto articlePriceResponseDto = new ArticlePriceResponseDto(ConstantsInfTest.ID
                ,ConstantsInfTest.PRICE,ConstantsInfTest.QUANTITY);

        List<ArticlePriceResponseDto> articlePriceResponseDtoList = new ArrayList<>();
        articlePriceResponseDtoList.add(articlePriceResponseDto);

        List<ArticlePriceResponse> articlePriceResponse = cartEntityMapper
                .articlePriceResponseDtosToArticlePriceResponse(articlePriceResponseDtoList);


        Assertions.assertNotNull(articlePriceResponse);
    }

    @Test
    void testArticlePriceResponseDtosToArticlePriceResponseNull() {
        List<ArticlePriceResponse> articlePriceResponse = cartEntityMapper
                .articlePriceResponseDtosToArticlePriceResponse(null);

        assertNull(articlePriceResponse);
    }
}