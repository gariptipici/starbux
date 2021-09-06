package com.bestseller.starbux.common.model;

import com.bestseller.starbux.shop.model.CartItem;
import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractProduct {


    @JoinColumn(name = "CART_ITEM_ID")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private CartItem cartItem;

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}
