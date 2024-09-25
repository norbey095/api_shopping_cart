package com.emazon.api_shopping_cart.domain.model.stock;

import java.util.List;

public class ArticleResponse {

    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
    private BrandArticleResponse brand;
    private List<CategoryResponseList> categories;

    public ArticleResponse() {
    }

    public ArticleResponse(Integer id, String name, String description, Integer quantity, double price,
                           BrandArticleResponse brand, List<CategoryResponseList> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BrandArticleResponse getBrand() {
        return brand;
    }

    public void setBrand(BrandArticleResponse brand) {
        this.brand = brand;
    }

    public List<CategoryResponseList> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponseList> categories) {
        this.categories = categories;
    }
}
