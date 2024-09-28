package com.emazon.api_shopping_cart.domain.model.stock;

public class ArticlePriceResponse {

    private Integer id;
    private double price;
    private Integer quantity;

    public ArticlePriceResponse() {
    }

    public ArticlePriceResponse(Integer id, double price, Integer quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
