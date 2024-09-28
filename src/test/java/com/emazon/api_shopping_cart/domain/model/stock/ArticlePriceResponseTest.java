package com.emazon.api_shopping_cart.domain.model.stock;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArticlePriceResponseTest {

    @Test
    void shouldCreateArticleWhenNameAndDescriptionAreValidSet() {
        ArticlePriceResponse articlePriceResponse = new ArticlePriceResponse(ConstantsDomain.NUMBER_1,ConstantsDomain.PRICE,
                ConstantsDomain.NUMBER_1);


        assertNotNull(articlePriceResponse);
        assertEquals(ConstantsDomain.NUMBER_1, articlePriceResponse.getId());
        assertEquals(ConstantsDomain.PRICE, articlePriceResponse.getPrice());
        assertEquals(ConstantsDomain.NUMBER_1, articlePriceResponse.getQuantity());
    }
}
