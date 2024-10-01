package com.emazon.api_shopping_cart.domain.model.report;

public class ArticleDetail {

    private Integer articleId;
    private String name;
    private double unitPrice;
    private Integer quantity;

    public ArticleDetail() {
    }

    public ArticleDetail(Integer articleId, String name, double unitPrice, Integer quantity) {
        this.articleId = articleId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
