package com.emazon.api_shopping_cart.domain.model.cartdetail;

import java.util.List;

public class CartDetailResponse {

        private List<CartDetail> cartDetail;
        private double totalPrice;

        public CartDetailResponse() {
        }

        public CartDetailResponse(List<CartDetail> cartDetail, double totalPrice) {
                this.cartDetail = cartDetail;
                this.totalPrice = totalPrice;
        }

        public List<CartDetail> getCartDetail() {
                return cartDetail;
        }

        public void setCartDetail(List<CartDetail> cartDetail) {
                this.cartDetail = cartDetail;
        }

        public double getTotalPrice() {
                return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
        }
}
