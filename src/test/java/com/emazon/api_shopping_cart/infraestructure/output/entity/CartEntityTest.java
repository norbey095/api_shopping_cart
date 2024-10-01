package com.emazon.api_shopping_cart.infraestructure.output.entity;

import com.emazon.api_shopping_cart.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CartEntityTest {

    private CartEntity cartEntity;

    @Test
    void testCartEntitySet() {
        LocalDateTime date = LocalDateTime.now();
        cartEntity = new CartEntity();
        cartEntity.setId(ConstantsInfTest.ID);
        cartEntity.setEmail(ConstantsInfTest.EMAIL);
        cartEntity.setIdArticle(ConstantsInfTest.ID_ARTICLE);
        cartEntity.setQuantity(ConstantsInfTest.QUANTITY);
        cartEntity.setCreateDate(date);
        cartEntity.setUpdateDate(date);

        assertNotNull(cartEntity);
        assertEquals(ConstantsInfTest.ID, cartEntity.getId());
        assertEquals(ConstantsInfTest.EMAIL, cartEntity.getEmail());
        assertEquals(ConstantsInfTest.ID_ARTICLE, cartEntity.getIdArticle());
        assertEquals(ConstantsInfTest.QUANTITY, cartEntity.getQuantity());
        assertEquals(date, cartEntity.getCreateDate());
        assertEquals(date, cartEntity.getUpdateDate());
    }

    @Test
    void testCartEntityConstructor() {
        LocalDateTime date = LocalDateTime.now();
        cartEntity = new CartEntity(ConstantsInfTest.ID,ConstantsInfTest.EMAIL
                ,ConstantsInfTest.ID_ARTICLE,ConstantsInfTest.QUANTITY,date,date);

        assertNotNull(cartEntity);
        assertEquals(ConstantsInfTest.ID, cartEntity.getId());
        assertEquals(ConstantsInfTest.EMAIL, cartEntity.getEmail());
        assertEquals(ConstantsInfTest.ID_ARTICLE, cartEntity.getIdArticle());
        assertEquals(ConstantsInfTest.QUANTITY, cartEntity.getQuantity());
        assertEquals(date, cartEntity.getCreateDate());
        assertEquals(date, cartEntity.getUpdateDate());
    }
}