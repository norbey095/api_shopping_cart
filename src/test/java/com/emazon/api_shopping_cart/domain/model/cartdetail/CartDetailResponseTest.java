package com.emazon.api_shopping_cart.domain.model.cartdetail;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartDetailResponseTest {

    @Test
    void shouldCartDetailResponseConstructor() {
        CartDetailResponse cartDetailResponse = new CartDetailResponse(new ArrayList<>(),ConstantsDomain.PRICE);

        assertNotNull(cartDetailResponse);
        assertEquals(ConstantsDomain.PRICE, cartDetailResponse.getTotalPrice());
        assertEquals(new ArrayList<>(), cartDetailResponse.getCartDetail());
    }

    @Test
    void shouldCartDetailResponseSet() {
        CartDetailResponse cartDetailResponse = new CartDetailResponse();
        cartDetailResponse.setTotalPrice(ConstantsDomain.PRICE);
        cartDetailResponse.setCartDetail(new ArrayList<>());

        assertNotNull(cartDetailResponse);
        assertEquals(ConstantsDomain.PRICE, cartDetailResponse.getTotalPrice());
        assertEquals(new ArrayList<>(), cartDetailResponse.getCartDetail());
    }
}
