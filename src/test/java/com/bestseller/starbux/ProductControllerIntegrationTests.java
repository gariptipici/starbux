package com.bestseller.starbux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.bestseller.starbux.common.dto.ProductDto;
import com.bestseller.starbux.common.dto.SideProductDto;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = StarbuxApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql({"classpath:/integrationdata.sql" })
public class ProductControllerIntegrationTests
{
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;


  @Test
  public void testAllProducts(){
    assertEquals(4, this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/products", List.class).size());

    assertEquals(4, this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/sideproducts", List.class).size());
  }






}