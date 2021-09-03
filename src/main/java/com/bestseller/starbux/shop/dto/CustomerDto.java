package com.bestseller.starbux.shop.dto;

import com.bestseller.starbux.common.dto.BaseDto;

public class CustomerDto extends BaseDto {
    private CartDto cart;

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }
}
