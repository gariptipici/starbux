package com.bestseller.starbux.shop.model;

import com.bestseller.starbux.common.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@MappedSuperclass
public abstract class AbstractOrder extends BaseEntity {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @Column(name = "AMOUNT")
    private BigDecimal amount;


}
