package com.emazon.api_shopping_cart.application.handler;

import com.emazon.api_shopping_cart.application.dto.cart.CartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cart.ResponseSuccess;
import com.emazon.api_shopping_cart.application.dto.cartdetail.ArticleCartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cartdetail.CartDetailResponseDto;
import com.emazon.api_shopping_cart.application.mapper.CartMapper;
import com.emazon.api_shopping_cart.application.util.ConstantsApplication;
import com.emazon.api_shopping_cart.domain.api.ICartServicePort;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.model.cartdetail.ArticleCartRequest;
import com.emazon.api_shopping_cart.domain.model.cartdetail.CartDetailResponse;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class CartHandlerTest {

    @InjectMocks
    private CartHandler cartHandler;

    @Mock
    private CartMapper cartMapper;

    @Mock
    private ICartServicePort cartSaveServicePort;

    private CartRequestDto cartSaveRequestDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cartSaveRequestDto = new CartRequestDto(ConstantsApplication.ID,
                ConstantsApplication.QUANTITY);
    }

    @Test
    void shouldSaveCart() {
        LocalDateTime date = LocalDateTime.now();
        CartSave cartSave = new CartSave(ConstantsApplication.ID, ConstantsApplication.EMAIL
                , ConstantsApplication.ID_ARTICLE
                , ConstantsApplication.QUANTITY, date, date);

        when(cartMapper.cartSaveRequestDtoToCartSave(cartSaveRequestDto)).thenReturn(cartSave);

        cartHandler.saveArticle(cartSaveRequestDto);

        Mockito.verify(cartMapper, Mockito.times(ConstantsApplication.NUMBER_1)).
                cartSaveRequestDtoToCartSave(cartSaveRequestDto);
    }

    @Test
    void shouldDeleteArticle() {
        Integer idArticle = ConstantsApplication.ID_ARTICLE;

        ResponseSuccess responseSuccess=cartHandler.deleteArticle(idArticle);

        verify(cartSaveServicePort, times(ConstantsApplication.NUMBER_1))
                .deleteCart(idArticle);
        Assertions.assertEquals(ConstantsApplication.DELETE_CORRECT, responseSuccess.getMessages());
        Assertions.assertEquals(HttpStatus.OK.toString(), responseSuccess.getStatus());
    }

    @Test
    void shouldGetCart() {
        ArticleCartRequestDto articleCartRequestDto = new ArticleCartRequestDto();
        articleCartRequestDto.setSize(ConstantsInfTest.NUMBER_1);
        articleCartRequestDto.setPage(ConstantsInfTest.NUMBER_1);

        ArticleCartRequest articleCartRequest = new ArticleCartRequest();
        articleCartRequest.setSize(ConstantsInfTest.NUMBER_1);
        articleCartRequest.setPage(ConstantsInfTest.NUMBER_1);


        when(cartMapper.articleCartRequestDtoToArticleCartRequest(articleCartRequestDto)).thenReturn(articleCartRequest);
        when(cartMapper.cartDetailResponseToCartDetailResponseDto(new CartDetailResponse()))
                .thenReturn(new CartDetailResponseDto());

        cartHandler.getCart(articleCartRequestDto);

        Mockito.verify(cartMapper, Mockito.times(ConstantsApplication.NUMBER_1)).
                articleCartRequestDtoToArticleCartRequest(articleCartRequestDto);
        Mockito.verify(cartMapper, Mockito.times(ConstantsApplication.NUMBER_1)).
                cartDetailResponseToCartDetailResponseDto(Mockito.any());
    }
}
