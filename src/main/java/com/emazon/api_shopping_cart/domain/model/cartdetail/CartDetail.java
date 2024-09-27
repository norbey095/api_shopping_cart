package com.emazon.api_shopping_cart.domain.model.cartdetail;


public class CartDetail {

        private String name;
        private Integer quantity;
        private double unitPrice;
        private double subPrice;
        private String message;

        public CartDetail() {
        }

        public CartDetail(String name, Integer quantity, double unitPrice, double subPrice, String message) {
                this.name = name;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
                this.subPrice = subPrice;
                this.message = message;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Integer getQuantity() {
                return quantity;
        }

        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }

        public double getUnitPrice() {
                return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
        }

        public double getSubPrice() {
                return subPrice;
        }

        public void setSubPrice(double subPrice) {
                this.subPrice = subPrice;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }
}
