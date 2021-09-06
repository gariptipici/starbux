package com.bestseller.starbux.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "ORDERR")
public class Order extends AbstractOrder {

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}