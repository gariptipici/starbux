package com.bestseller.starbux.shop.model;


import javax.persistence.*;

@Entity
@Table(name = "CART")
public class Cart extends AbstractOrder {

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
