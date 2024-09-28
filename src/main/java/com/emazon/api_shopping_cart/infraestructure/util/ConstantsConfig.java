package com.emazon.api_shopping_cart.infraestructure.util;

public class ConstantsConfig {

    public static final String COMA = "";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String APPLICATION_JSON = "application/json";
    public static final String TOKEN_INVALID = "Invalid token: signature does not match.";
    public static final String URL_STOCK_PORT = "http://localhost:9090";
    public static final String STOCK = "stock";
    public static final String URL_STOCK_ARTICLEID = "/stock/article/{articleId}";
    public static final String URL_STOCK_LIST_ARTICLE = "/stock/article/getItemsCart";
    public static final Integer SEVEN_LETTERS = 7;
    public static final String URL_TRANSACTION_PORT = "http://localhost:9092";
    public static final String TRANSACTION = "transaction";
    public static final String URL_TRANSACTION_ARTICLEID = "/supply/{articleId}";
    public static final String FALSE = "false";
    public static final String URL_STOCK_PRICE = "/stock/article/getPriceByIds";


    private ConstantsConfig() {
    }
}