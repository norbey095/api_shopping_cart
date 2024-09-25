package com.emazon.api_shopping_cart.domain.model;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartTest {

    @Test
    void testCartSave() {
        LocalDate date = LocalDate.now();
        CartSave article = new CartSave(ConstantsDomain.ID, ConstantsDomain.EMAIL
                , ConstantsDomain.ID_ARTICLE
                , ConstantsDomain.QUANTITY, date, date);

        assertNotNull(article);
        assertEquals(ConstantsDomain.ID, article.getId());
        assertEquals(ConstantsDomain.EMAIL, article.getEmail());
        assertEquals(ConstantsDomain.ID_ARTICLE, article.getIdArticle());
        assertEquals(ConstantsDomain.QUANTITY, article.getQuantity());
        assertEquals(date, article.getCreateDate());
        assertEquals(date, article.getUpdateDate());
    }
}
