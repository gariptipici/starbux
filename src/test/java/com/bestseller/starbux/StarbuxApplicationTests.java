package com.bestseller.starbux;

import com.bestseller.starbux.admin.controller.ProductController;
import com.bestseller.starbux.admin.controller.ReportController;
import com.bestseller.starbux.admin.controller.SideProductController;
import com.bestseller.starbux.admin.service.CustomerInfoReportService;
import com.bestseller.starbux.admin.service.ProductService;
import com.bestseller.starbux.shop.controller.CartController;
import com.bestseller.starbux.shop.controller.CustomerController;
import com.bestseller.starbux.shop.controller.OrderController;
import com.bestseller.starbux.shop.repository.CartRepository;
import com.bestseller.starbux.shop.repository.OrderRepository;
import com.bestseller.starbux.shop.service.CartService;
import com.bestseller.starbux.shop.service.CustomerService;
import com.bestseller.starbux.shop.service.DiscountService;
import com.bestseller.starbux.shop.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StarbuxApplicationTests {

  @Autowired
  ProductController productController;
  @Autowired
  ReportController reportController;
  @Autowired
  SideProductController sideProductController;
  @Autowired
  CustomerInfoReportService customerInfoReportService;
  @Autowired
  ProductService productService;
  @Autowired
  CartController cartController;
  @Autowired
  CustomerController customerController;
  @Autowired
  OrderController orderController;
  @Autowired
  CartRepository cartRepository;
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CartService cartService;
  @Autowired
  CustomerService customerService;
  @Autowired
  DiscountService discountService;
  @Autowired
  OrderService orderService;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(productController);
    Assertions.assertNotNull(reportController);
    Assertions.assertNotNull(sideProductController);
    Assertions.assertNotNull(customerInfoReportService);
    Assertions.assertNotNull(productService);
    Assertions.assertNotNull(cartController);
    Assertions.assertNotNull(customerController);
    Assertions.assertNotNull(orderController);
    Assertions.assertNotNull(cartRepository);
    Assertions.assertNotNull(orderRepository);
    Assertions.assertNotNull(cartService);
    Assertions.assertNotNull(customerService);
    Assertions.assertNotNull(discountService);
    Assertions.assertNotNull(orderService);
  }

}
