package com.bestseller.starbux.common.model;

import com.bestseller.starbux.shop.model.CartItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "SIDE_PRODUCT")
public class SideProduct extends AbstractProduct {

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    private CartItem cartItem;
}
