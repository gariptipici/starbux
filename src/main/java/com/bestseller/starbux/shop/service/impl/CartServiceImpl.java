package com.bestseller.starbux.shop.service.impl;

import com.bestseller.starbux.admin.exception.ProductNotFoundException;
import com.bestseller.starbux.admin.repository.ProductRepository;
import com.bestseller.starbux.admin.repository.SideProductRepository;
import com.bestseller.starbux.common.dto.BaseDto;
import com.bestseller.starbux.common.dto.SideProductDto;
import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import com.bestseller.starbux.shop.dto.CartDto;
import com.bestseller.starbux.shop.exception.CartItemNotfoundException;
import com.bestseller.starbux.shop.exception.CartNotfoundException;
import com.bestseller.starbux.shop.mapper.CartMapper;
import com.bestseller.starbux.shop.model.Cart;
import com.bestseller.starbux.shop.model.CartItem;
import com.bestseller.starbux.shop.repository.CartItemRepository;
import com.bestseller.starbux.shop.repository.CartRepository;
import com.bestseller.starbux.shop.service.CartService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final SideProductRepository sideProductRepository;
    private final CartMapper cartMapper = CartMapper.INSTANCE;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, SideProductRepository sideProductRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.sideProductRepository = sideProductRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CartDto readCart(Long customerId, Long cartId) {
        return cartMapper.cartToCartDto(
                cartRepository.findById_AndCustomerId(cartId, customerId).orElseThrow(CartNotfoundException::new));
    }

    @Override
    @Transactional
    @Modifying
    public CartDto updateCart(Long customerId, Long cartId, CartDto cartDto) {
        Cart updated =cartRepository.findById_AndCustomerId(cartId, customerId).map(existingCart -> {
            List<CartItem> newItems = cartDto.getCartItems().stream().map(itemDto -> {
                CartItem item = new CartItem();
                item.setQuantity(itemDto.getQuantity());
                Product product = productRepository.findById(itemDto.getProduct().getId())
                        .orElseThrow(ProductNotFoundException::new);
                item.setProduct(product);
                List<Long> sideProductIds = itemDto.getSideProducts().stream().map(BaseDto::getId)
                        .collect(Collectors.toList());
                Iterable<SideProduct> sideProducts = sideProductRepository.findAllById(sideProductIds);
                item.setSideProducts(StreamSupport.stream(sideProducts.spliterator(), false)
                        .collect(Collectors.toList()));
                item.setQuantity(itemDto.getQuantity());
                return item;
            }).collect(Collectors.toList());
            existingCart.setCartItems(newItems);

            return existingCart;
        }).orElseThrow(CartNotfoundException::new);


        Cart x = cartRepository.save(updated);
        return cartMapper.cartToCartDto(x);
    }

    @Override
    @Transactional
    public CartDto emptyCart(Long customerId, Long cartId) {
        Cart updated = cartRepository.findById_AndCustomerId(cartId, customerId).map(existing -> {
            Cart empty = new Cart();
            empty.setId(existing.getId());
            return empty;
        }).orElseThrow(CartNotfoundException::new);

        return cartMapper.cartToCartDto(updated);
    }
}
