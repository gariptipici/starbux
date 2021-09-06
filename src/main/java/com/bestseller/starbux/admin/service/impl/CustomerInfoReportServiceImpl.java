package com.bestseller.starbux.admin.service.impl;

import com.bestseller.starbux.admin.dto.CustomerInfoDto;
import com.bestseller.starbux.admin.dto.ProductInfoDto;
import com.bestseller.starbux.admin.repository.CustomerInfoReportRepository;
import com.bestseller.starbux.admin.service.CustomerInfoReportService;
import com.bestseller.starbux.common.model.Product;
import com.bestseller.starbux.common.model.SideProduct;
import com.bestseller.starbux.shop.model.CartItem;
import com.bestseller.starbux.shop.repository.CartItemRepository;
import com.bestseller.starbux.shop.repository.OrderRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoReportServiceImpl implements CustomerInfoReportService {

  private final CustomerInfoReportRepository customerInfoReportRepository;
  private final CartItemRepository cartItemRepository;

  public CustomerInfoReportServiceImpl(
      CustomerInfoReportRepository customerInfoReportRepository,
      CartItemRepository cartItemRepository) {
    this.customerInfoReportRepository = customerInfoReportRepository;
    this.cartItemRepository = cartItemRepository;
  }

  @Override
  public List<CustomerInfoDto> getTotalAmountPerCustomer() {
    List<Object[]> reports = customerInfoReportRepository.getTotalAmountPerCustomer();
    return reports.stream().map(objects -> {
      CustomerInfoDto dto = new CustomerInfoDto();
      dto.setCustomerId(((BigInteger) objects[0]).longValue());
      dto.setOrderTotalAmount((BigDecimal) objects[1]);
      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  public List<ProductInfoDto> getMostUsedToppingPerProduct() {
    Map<Product, List<CartItem>> cartItemsByProduct = StreamSupport
        .stream(cartItemRepository.findAll().spliterator(), false).collect(Collectors.groupingBy(
            CartItem::getProduct));

    return cartItemsByProduct.keySet().stream().map(key -> {
      Long mostSelectedSideProductId = cartItemsByProduct.get(key).stream()
          .flatMap(item -> item.getSideProducts().stream())
          .collect(Collectors.groupingBy(
              SideProduct::getId, Collectors.counting())).entrySet().stream()
          .max(Entry.comparingByValue()).map(
              Entry::getKey).orElse(null);
      ProductInfoDto dto = new ProductInfoDto();
      dto.setProductId(key.getId());
      dto.setSideProductId(mostSelectedSideProductId);
      return dto;
    }).collect(Collectors.toList());
  }
}
