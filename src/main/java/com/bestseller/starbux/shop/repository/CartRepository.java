package com.bestseller.starbux.shop.repository;

import com.bestseller.starbux.shop.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByCustomer_Id_AndId(Long customerId, Long cartId);
}
