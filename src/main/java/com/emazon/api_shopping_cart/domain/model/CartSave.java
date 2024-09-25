package com.emazon.api_shopping_cart.domain.model;

import java.time.LocalDate;

public class CartSave {
    private Integer id;
    private String email;
    private Integer idArticle;
    private Integer quantity;
    private LocalDate createDate;
    private LocalDate updateDate;

    public CartSave() {
    }

    public CartSave(Integer id, String email, Integer idArticle, Integer quantity, LocalDate createDate, LocalDate updateDate) {
        this.id = id;
        this.email = email;
        this.idArticle = idArticle;
        this.quantity = quantity;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}
