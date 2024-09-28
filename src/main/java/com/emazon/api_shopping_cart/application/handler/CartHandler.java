package com.emazon.api_shopping_cart.application.handler;

import com.emazon.api_shopping_cart.application.dto.cart.CartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cart.ResponseSuccess;
import com.emazon.api_shopping_cart.application.dto.cartdetail.CartDetailResponseDto;
import com.emazon.api_shopping_cart.application.mapper.CartMapper;
import com.emazon.api_shopping_cart.application.util.ConstantsDto;
import com.emazon.api_shopping_cart.domain.api.ICartServicePort;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.model.cartdetail.CartDetailResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class CartHandler implements ICartHandler {

    private final ICartServicePort cartSaveServicePort;
    private final CartMapper cartMapper;

    @Override
    public ResponseSuccess saveArticle(CartRequestDto cartSaveRequestDto) {
        CartSave cartSave = cartMapper.cartSaveRequestDtoToCartSave(cartSaveRequestDto);
        cartSaveServicePort.cartSave(cartSave);
        return new ResponseSuccess(ConstantsDto.PRODUCT_ADD,HttpStatus.OK.toString());
    }

    @Override
    public ResponseSuccess deleteArticle(Integer idArticle) {
        cartSaveServicePort.deleteCart(idArticle);
        return new ResponseSuccess(ConstantsDto.DELETE_CORRECT,HttpStatus.OK.toString());
    }

    @Override
    public CartDetailResponseDto getCart(Integer page, Integer size,boolean descending,
                                         String categoryName,String brandName) {
        CartDetailResponse cartDetailResponse = cartSaveServicePort.getCart(page,size,descending,categoryName,brandName);
        return cartMapper.cartDetailResponseToCartDetailResponseDto(cartDetailResponse);
    }

    @Override
    public ResponseSuccess buyCart() {
        cartSaveServicePort.buyArticle();
        return new ResponseSuccess(ConstantsDto.BUY_CORRECT,HttpStatus.OK.toString());
    }
}
