package com.bestseller.starbux.shop.dto;

import com.bestseller.starbux.common.dto.BaseDto;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.List;

public class AbstractOrderDto extends BaseDto {

    @ApiModelProperty(notes = "Total amount of cart/order.", example = "8", hidden = true, position = 2)
    private BigDecimal amount;

    @ApiModelProperty(notes = "Total discount of cart/order.", example = "2", hidden = true, position = 3)
    private BigDecimal discount;

    @ApiModelProperty(notes = "Items of cart/order.", position = 4)
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
