package com.emazon.api_shopping_cart.domain.model.cartdetail;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartDetailTest {

    @Test
    void shouldCreateCartDetailConstructor() {
        CartDetail cart = new CartDetail(ConstantsDomain.FIELD_NAME, ConstantsDomain.NUMBER_2
                ,ConstantsDomain.PRICE,ConstantsDomain.PRICE,ConstantsDomain.FIELD_NAME);

        assertNotNull(cart);
        assertEquals(ConstantsDomain.FIELD_NAME, cart.getName());
        assertEquals(ConstantsDomain.NUMBER_2, cart.getQuantity());
        assertEquals(ConstantsDomain.PRICE, cart.getUnitPrice());
        assertEquals(ConstantsDomain.PRICE, cart.getSubPrice());
        assertEquals(ConstantsDomain.FIELD_NAME, cart.getMessage());
    }

    @Test
    void shouldCreateCartDetailSet() {
        CartDetail cart = new CartDetail();

        cart.setName(ConstantsDomain.FIELD_NAME);
        cart.setQuantity(ConstantsDomain.NUMBER_2);
        cart.setUnitPrice(ConstantsDomain.PRICE);
        cart.setSubPrice(ConstantsDomain.PRICE);
        cart.setMessage(ConstantsDomain.FIELD_NAME);


        assertNotNull(cart);
        assertEquals(ConstantsDomain.FIELD_NAME, cart.getName());
        assertEquals(ConstantsDomain.NUMBER_2, cart.getQuantity());
        assertEquals(ConstantsDomain.PRICE, cart.getUnitPrice());
        assertEquals(ConstantsDomain.PRICE, cart.getSubPrice());
        assertEquals(ConstantsDomain.FIELD_NAME, cart.getMessage());
    }
}
