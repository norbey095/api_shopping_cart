package com.emazon.api_shopping_cart.domain.model.report;

import java.time.LocalDateTime;
import java.util.List;

public class ReportBuy {

    private String id;
    private String userName;
    private LocalDateTime createDate;
    private LocalDateTime buyDate;
    private double totalPrice;
    private List<ArticleDetail> articleDetails;

    public ReportBuy() {
    }

    public ReportBuy(String id, String userName, LocalDateTime createDate, LocalDateTime buyDate, double totalPrice,
                     List<ArticleDetail> articleDetails) {
        this.id = id;
        this.userName = userName;
        this.createDate = createDate;
        this.buyDate = buyDate;
        this.totalPrice = totalPrice;
        this.articleDetails = articleDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ArticleDetail> getArticleDetails() {
        return articleDetails;
    }

    public void setArticleDetails(List<ArticleDetail> articleDetails) {
        this.articleDetails = articleDetails;
    }
}
