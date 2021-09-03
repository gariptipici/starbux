package com.bestseller.starbux.common.model;

import com.bestseller.starbux.shop.model.CartItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractProduct {


    @OneToOne
    private CartItem cartItem;
}
