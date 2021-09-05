package com.bestseller.starbux.admin.repository;

import com.bestseller.starbux.admin.model.CustomerInfo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoReportRepository extends CrudRepository<CustomerInfo, Long> {
  @Query(value = "SELECT CUSTOMER_ID, SUM(AMOUNT) - SUM(DISCOUNT) AS ORDER_TOTAL_AMOUNT FROM ORDERR GROUP BY CUSTOMER_ID", nativeQuery = true)
  List<Object[]> getTotalAmountPerCustomer();
}


