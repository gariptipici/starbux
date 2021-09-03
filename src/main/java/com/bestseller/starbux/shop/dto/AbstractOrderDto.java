package com.bestseller.starbux.shop.dto;

import java.math.BigDecimal;
import java.util.List;

public class AbstractOrderDto{
    private BigDecimal amount;
    private List<CartItemDto> cartItems;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
