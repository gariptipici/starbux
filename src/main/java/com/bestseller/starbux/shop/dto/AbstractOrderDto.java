package com.bestseller.starbux.shop.dto;

import com.bestseller.starbux.common.dto.BaseDto;

import java.math.BigDecimal;
import java.util.List;

public class AbstractOrderDto extends BaseDto {
    private BigDecimal amount;
    private BigDecimal discount;
    private List<CartItemDto> cartItems;

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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
