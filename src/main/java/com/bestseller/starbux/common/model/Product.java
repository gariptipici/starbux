package com.bestseller.starbux.common.model;

import com.bestseller.starbux.shop.model.CartItem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractProduct {


    @JoinColumn(name = "CART_ITEM_ID")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private CartItem cartItem;
}
