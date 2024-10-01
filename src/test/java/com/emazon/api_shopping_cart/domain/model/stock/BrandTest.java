package com.emazon.api_shopping_cart.domain.model.stock;

import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BrandTest {

    @Test
    void shouldCreateBrandWhenNameAndDescriptionAreValid() {
        BrandArticleResponse brand = new BrandArticleResponse (ConstantsDomain.NUMBER_1, ConstantsDomain.FIELD_NAME);

        assertNotNull(brand);
        assertEquals(ConstantsDomain.NUMBER_1, brand.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, brand.getName());
    }

    @Test
    void shouldCreateBrandWhenNameAndDescriptionAreValidSet() {
        BrandArticleResponse brand = new BrandArticleResponse ();
        brand.setId(ConstantsDomain.NUMBER_1);
        brand.setName(ConstantsDomain.FIELD_NAME);

        assertNotNull(brand);
        assertEquals(ConstantsDomain.NUMBER_1, brand.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, brand.getName());
    }
}
