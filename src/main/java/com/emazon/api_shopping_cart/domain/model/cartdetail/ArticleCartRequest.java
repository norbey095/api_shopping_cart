package com.emazon.api_shopping_cart.domain.model.cartdetail;

import java.util.List;

public class ArticleCartRequest {

    private Integer page;
    private Integer size;
    private boolean descending;
    private List<Integer> articleIds;
    private String categoryName;
    private String brandName;

    public ArticleCartRequest() {
    }

    public ArticleCartRequest(Integer page, Integer size, boolean descending, List<Integer> articleIds,
                              String categoryName, String brandName) {
        this.page = page;
        this.size = size;
        this.descending = descending;
        this.articleIds = articleIds;
        this.categoryName = categoryName;
        this.brandName = brandName;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }

    public List<Integer> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Integer> articleIds) {
        this.articleIds = articleIds;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
