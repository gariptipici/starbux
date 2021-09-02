package com.bestseller.starbux.admin.repository;

import com.bestseller.starbux.common.model.SideProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SideProductRepository extends CrudRepository<SideProduct, Long> {
}
