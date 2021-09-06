package com.bestseller.starbux.shop.model;

import com.bestseller.starbux.common.model.BaseEntity;
import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "CART_ITEM")
public class CartItem extends BaseEntity {
    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CART_ITEM_SIDE_PRODUCTS",
            joinColumns = @JoinColumn(name = "CART_ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "SIDE_PRODUCTS_ID"))
    private List<SideProduct> sideProducts;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<SideProduct> getSideProducts() {
        return sideProducts;
    }

    public void setSideProducts(List<SideProduct> sideProducts) {
        this.sideProducts = sideProducts;
    }
}
