package com.emazon.api_shopping_cart.application.handler;

import com.emazon.api_shopping_cart.application.dto.CartSaveRequestDto;
import com.emazon.api_shopping_cart.application.dto.ResponseSuccess;
import com.emazon.api_shopping_cart.application.mapper.CartMapper;
import com.emazon.api_shopping_cart.application.util.ConstantsApplication;
import com.emazon.api_shopping_cart.domain.api.ICartServicePort;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class CartHandlerTest {

    @InjectMocks
    private CartHandler cartHandler;

    @Mock
    private CartMapper cartMapper;

    @Mock
    private ICartServicePort cartSaveServicePort;

    private CartSaveRequestDto cartSaveRequestDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        cartSaveRequestDto = new CartSaveRequestDto(ConstantsApplication.ID,
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
}
