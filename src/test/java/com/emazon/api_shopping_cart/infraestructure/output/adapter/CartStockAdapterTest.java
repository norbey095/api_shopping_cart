package com.emazon.api_shopping_cart.infraestructure.output.adapter;

import com.emazon.api_shopping_cart.application.dto.stock.ArticleResponseDto;
import com.emazon.api_shopping_cart.domain.model.stock.ArticleResponse;
import com.emazon.api_shopping_cart.infraestructure.configuration.feign.IFeignClientStock;
import com.emazon.api_shopping_cart.infraestructure.output.mapper.ICartEntityMapper;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartStockAdapterTest {

    @Mock
    private IFeignClientStock feignClientStock;

    @Mock
    private ICartEntityMapper cartEntityMapper;

    @InjectMocks
    private CartStockAdapter cartStockAdapter;

    @Test
    void testExistArticleById() {
        Integer articleId = ConstantsInfTest.NUMBER_1;
        ArticleResponseDto articleResponseDto = new ArticleResponseDto();
        ArticleResponse expectedArticleResponse = new ArticleResponse();

        Mockito.when(feignClientStock.existById(articleId)).thenReturn(articleResponseDto);
        Mockito.when(cartEntityMapper.articleResponseDtoToArticleResponse(articleResponseDto))
                .thenReturn(expectedArticleResponse);

        ArticleResponse actualArticleResponse = cartStockAdapter.existArticleById(articleId);

        Assertions.assertEquals(expectedArticleResponse, actualArticleResponse);
        Mockito.verify(feignClientStock).existById(articleId);
        Mockito.verify(cartEntityMapper).articleResponseDtoToArticleResponse(articleResponseDto);
    }
}