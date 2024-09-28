package com.emazon.api_shopping_cart.infraestructure.output.adapter;

import com.emazon.api_shopping_cart.application.dto.stock.ArticleResponseDto;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientStock;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartStockMapper;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class CartStockAdapterTest {

    @Mock
    private IFeignClientStock feignClientStock;

    @Mock
    private ICartStockMapper cartStockMapper;

    @InjectMocks
    private CartStockAdapter cartStockAdapter;

    @Test
    void testExistArticleById() {
        Integer articleId = ConstantsInfTest.NUMBER_1;
        ArticleResponseDto articleResponseDto = new ArticleResponseDto();
        ArticleResponse expectedArticleResponse = new ArticleResponse();

        Mockito.when(feignClientStock.existById(articleId)).thenReturn(articleResponseDto);
        Mockito.when(cartStockMapper.articleResponseDtoToArticleResponse(articleResponseDto))
                .thenReturn(expectedArticleResponse);

        ArticleResponse actualArticleResponse = cartStockAdapter.existArticleById(articleId);

        Assertions.assertEquals(expectedArticleResponse, actualArticleResponse);
        Mockito.verify(feignClientStock).existById(articleId);
        Mockito.verify(cartStockMapper).articleResponseDtoToArticleResponse(articleResponseDto);
    }

    @Test
    void testGetArticleDetails() {
        Mockito.when(feignClientStock.getArticleDetails(ConstantsInfTest.NUMBER_1,
                ConstantsInfTest.NUMBER_0
                ,true,new ArrayList<>(), ConstantsInfTest.NAME, ConstantsInfTest.NAME))
                .thenReturn(new ArrayList<>());
        Mockito.when(cartStockMapper.articleResponseDtoListToArticleResponseList(Mockito.anyList()))
                .thenReturn(new ArrayList<>());

        cartStockAdapter.getArticleDetails(ConstantsInfTest.NUMBER_1,
                ConstantsInfTest.NUMBER_0
                ,true,new ArrayList<>(), ConstantsInfTest.NAME, ConstantsInfTest.NAME);

        Mockito.verify(feignClientStock, Mockito.times(ConstantsInfTest.NUMBER_1))
                .getArticleDetails(ConstantsInfTest.NUMBER_1,
                        ConstantsInfTest.NUMBER_0
                        ,true,new ArrayList<>(), ConstantsInfTest.NAME, ConstantsInfTest.NAME);
        Mockito.verify(cartStockMapper, Mockito.times(ConstantsInfTest.NUMBER_1))
                .articleResponseDtoListToArticleResponseList(Mockito.anyList());
    }

    @Test
    void testGetPriceByIds() {
        Mockito.when(feignClientStock.getPriceByIds(new ArrayList<>()))
                .thenReturn(new ArrayList<>());
        Mockito.when(cartStockMapper.articlePriceResponseDtosToArticlePriceResponse(Mockito.anyList()))
                .thenReturn(new ArrayList<>());

        cartStockAdapter.getPriceByIds(new ArrayList<>());

        Mockito.verify(feignClientStock, Mockito.times(ConstantsInfTest.NUMBER_1))
                .getPriceByIds(new ArrayList<>());
        Mockito.verify(cartStockMapper, Mockito.times(ConstantsInfTest.NUMBER_1))
                .articlePriceResponseDtosToArticlePriceResponse(Mockito.anyList());
    }
}