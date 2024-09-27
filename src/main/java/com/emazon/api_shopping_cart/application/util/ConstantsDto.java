package com.emazon.api_shopping_cart.application.util;

import lombok.Getter;

@Getter
public class ConstantsDto {
    public static final String ID_ARTICLE_REQUIRED = "The parameter 'idArticle' cannot be empty";
    public static final String QUANTITY_REQUIRED = "The parameter 'quantity' cannot be empty";
    public static final String PRODUCT_ADD = "Product added successfully";
    public static final String EMAIL = "email";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateDate";
    public static final String ID = "id";
    public static final String ARTICLE_ID = "articleIds";
    public static final int NUMBER_0 = 0;
    public static final String QUANTITY_NOT_NEGATIVE = "The quantity cannot be negative";
    public static final String DELETE_CORRECT = "Has been successfully removed";

    private ConstantsDto() {

    }
}
