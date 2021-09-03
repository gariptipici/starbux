package com.bestseller.starbux.shop.mapper;

import com.bestseller.starbux.shop.dto.CartDto;
import com.bestseller.starbux.shop.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CartMapper {
    public static CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    public abstract Cart cartDtoToCart(CartDto cartDto);
    public abstract CartDto cartToCartDto(Cart cart);
}
