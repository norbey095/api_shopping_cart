package com.emazon.api_shopping_cart.domain.model.cartdetail;


public class CartDetail {

        private String name;
        private Integer quantityRequest;
        private Integer quantityAvailable;
        private double unitPrice;
        private double subPrice;
        private String message;

        public CartDetail() {
        }

        public CartDetail(String name, Integer quantityRequest, Integer quantityAvailable, double unitPrice,
                          double subPrice, String message) {
                this.name = name;
                this.quantityRequest = quantityRequest;
                this.quantityAvailable = quantityAvailable;
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

        public Integer getQuantityRequest() {
                return quantityRequest;
        }

        public void setQuantityRequest(Integer quantityRequest) {
                this.quantityRequest = quantityRequest;
        }

        public Integer getQuantityAvailable() {
                return quantityAvailable;
        }

        public void setQuantityAvailable(Integer quantityAvailable) {
                this.quantityAvailable = quantityAvailable;
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
