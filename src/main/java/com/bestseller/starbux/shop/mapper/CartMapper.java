package com.bestseller.starbux.shop.mapper;

import com.bestseller.starbux.shop.dto.CartDto;
import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CartMapper {
    public static CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    public abstract Order cartToOrder(Cart car);
    public abstract Cart cartDtoToCart(CartDto cartDto);
    public abstract CartDto cartToCartDto(Cart cart);
}
