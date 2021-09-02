package com.bestseller.starbux.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "CART")
@NoArgsConstructor
public class Order extends AbstractOrder{
    @Column(name = "DISCOUNT")
    private BigDecimal discount;
}