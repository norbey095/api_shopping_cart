package com.emazon.api_shopping_cart.application.mapper;

import com.emazon.api_shopping_cart.application.dto.cart.CartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cartdetail.ArticleCartRequestDto;
import com.emazon.api_shopping_cart.application.dto.cartdetail.CartDetailResponseDto;
import com.emazon.api_shopping_cart.application.util.ConstantsApplication;
import com.emazon.api_shopping_cart.domain.model.CartSave;
import com.emazon.api_shopping_cart.domain.model.cartdetail.ArticleCartRequest;
import com.emazon.api_shopping_cart.domain.model.cartdetail.CartDetail;
import com.emazon.api_shopping_cart.domain.model.cartdetail.CartDetailResponse;
import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartMapperTest {

    private final CartMapper cartMapper = Mappers.getMapper(CartMapper.class);

    @Test
    void testUserRequestDtoToUserSave() {
        CartRequestDto cartSaveRequestDto = new CartRequestDto(ConstantsApplication.ID_ARTICLE
                ,ConstantsApplication.QUANTITY);

        CartSave usertestSave = cartMapper.cartSaveRequestDtoToCartSave(cartSaveRequestDto);

        assertNotNull(usertestSave);
        assertEquals(cartSaveRequestDto.getIdArticle(),usertestSave.getIdArticle());
        assertEquals(cartSaveRequestDto.getQuantity(),usertestSave.getQuantity());
    }

    @Test
    void testArticleCartRequestDtoToArticleCartRequest() {
        ArticleCartRequestDto articleCartRequestDto = new ArticleCartRequestDto(ConstantsApplication.NUMBER_1,
                ConstantsApplication.NUMBER_1,true,ConstantsApplication.NAME,ConstantsApplication.NAME);

        ArticleCartRequest usertestSave = cartMapper.articleCartRequestDtoToArticleCartRequest(articleCartRequestDto);

        assertNotNull(usertestSave);
        assertEquals(usertestSave.getPage(),articleCartRequestDto.getPage());
        assertEquals(usertestSave.getSize(),articleCartRequestDto.getSize());
        assertEquals(usertestSave.getBrandName(),articleCartRequestDto.getBrandName());
        assertEquals(usertestSave.getCategoryName(),articleCartRequestDto.getCategoryName());
        assertTrue(usertestSave.isDescending());
    }

    @Test
    void testCartDetailResponseToCartDetailResponseDto() {
        CartDetailResponse cartDetailResponse = new CartDetailResponse();
        cartDetailResponse.setTotalPrice(ConstantsInfTest.PRICE);
        CartDetail cartDetail = new CartDetail(ConstantsApplication.NAME, ConstantsApplication.NUMBER_2
                ,ConstantsApplication.PRICE,ConstantsApplication.PRICE,ConstantsApplication.NAME);
        List<CartDetail> list = new ArrayList<>();
        list.add(cartDetail);

        cartDetailResponse.setCartDetail(list);

        CartDetailResponseDto cartDetailResponseDto = cartMapper
                .cartDetailResponseToCartDetailResponseDto(cartDetailResponse);

        assertNotNull(cartDetailResponseDto);
        assertEquals(cartDetailResponseDto.getTotalPrice(),cartDetailResponse.getTotalPrice());
    }



}