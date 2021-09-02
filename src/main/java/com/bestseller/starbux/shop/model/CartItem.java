package com.bestseller.starbux.shop.model;

import com.bestseller.starbux.common.model.BaseEntity;
import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "CART_ITEM")
public class CartItem extends BaseEntity {
    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private BigDecimal price = BigDecimal.ZERO;

    @OneToOne
    private Product product;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SideProduct> sideProducts;
}
