package com.emazon.api_shopping_cart.domain.model.stock;


public class BrandArticleResponse {

    private Integer id;
    private String name;

    public BrandArticleResponse() {
    }

    public BrandArticleResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
