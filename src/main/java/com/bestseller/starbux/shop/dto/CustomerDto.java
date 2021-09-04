package com.bestseller.starbux.shop.dto;

import com.bestseller.starbux.common.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing a customer of Starbux")
public class CustomerDto extends BaseDto {

    @ApiModelProperty(notes = "Class representing cart of the customer", hidden = true, position = 2)
    private CartDto cart;

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }
}
