package com.bestseller.starbux.shop.repository;

import com.bestseller.starbux.shop.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
