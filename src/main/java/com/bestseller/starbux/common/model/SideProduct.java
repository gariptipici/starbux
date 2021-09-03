package com.bestseller.starbux.common.model;

import com.bestseller.starbux.shop.model.CartItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "SIDE_PRODUCT")
public class SideProduct extends AbstractProduct {

    @ManyToOne
    private CartItem cartItem;
}
