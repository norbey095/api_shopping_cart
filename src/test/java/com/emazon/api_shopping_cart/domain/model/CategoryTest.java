package com.emazon.api_shopping_cart.domain.model;

import com.emazon.api_shopping_cart.domain.model.stock.CategoryResponseList;
import com.emazon.api_shopping_cart.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryTest {

    @Test
    void shouldCreateCategoryWhenNameAndDescriptionAreValid() {
        CategoryResponseList category = new CategoryResponseList(ConstantsDomain.NUMBER_1, ConstantsDomain.FIELD_NAME);

        assertNotNull(category);
        assertEquals(ConstantsDomain.NUMBER_1, category.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, category.getName());
    }

    @Test
    void shouldCreateCategoryWhenNameAndDescriptionAreValidSet() {
        CategoryResponseList category = new CategoryResponseList();
        category.setId(ConstantsDomain.NUMBER_1);
        category.setName(ConstantsDomain.FIELD_NAME);

        assertNotNull(category);
        assertEquals(ConstantsDomain.NUMBER_1, category.getId());
        assertEquals(ConstantsDomain.FIELD_NAME, category.getName());
    }

}
