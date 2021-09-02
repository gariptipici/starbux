package com.bestseller.starbux.shop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "CART")
public class Cart extends AbstractOrder{
}
