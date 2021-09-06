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

  @Test
  @DirtiesContext
  public void createTest() {
    ProductDto dto = new ProductDto();
    dto.setPrice(BigDecimal.TEN);
    dto.setProductName("testProductName");
    ProductDto result = this.restTemplate
        .postForObject("http://localhost:" + port + "/admin/products", dto, ProductDto.class);

    assertEquals(dto.getProductName(), result.getProductName());

    SideProductDto sideDto = new SideProductDto();
    sideDto.setPrice(BigDecimal.TEN);
    sideDto.setProductName("testSideName");
    SideProductDto sideResult = this.restTemplate
        .postForObject("http://localhost:" + port + "/admin/sideproducts", sideDto, SideProductDto.class);

    assertEquals(sideDto.getProductName(), sideResult.getProductName());
  }

  @Test
  public void readTest() {

    ProductDto result = this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/products/1", ProductDto.class);

    assertEquals("Black Coffee", result.getProductName());

    SideProductDto sideResult = this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/sideproducts/1", SideProductDto.class);
    assertEquals("Milk", sideResult.getProductName());
  }

  @Test
  @DirtiesContext
  public void updateTest() {
    ProductDto dto = new ProductDto();
    dto.setPrice(BigDecimal.TEN);
    dto.setProductName("updatedProductName");

    this.restTemplate.put("http://localhost:" + port + "/admin/products/1", dto);

    ProductDto result = this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/products/1", ProductDto.class);

    assertEquals(dto.getProductName(), result.getProductName());

    SideProductDto sideDto = new SideProductDto();
    sideDto.setPrice(BigDecimal.TEN);
    sideDto.setProductName("updatedSideProductName");

    this.restTemplate.put("http://localhost:" + port + "/admin/sideproducts/1", sideDto);

    SideProductDto sideResult = this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/sideproducts/1", SideProductDto.class);

    assertEquals(sideDto.getProductName(), sideResult.getProductName());
  }

  @Test
  @DirtiesContext
  public void deleteTest() {

    ProductDto dto = new ProductDto();
    dto.setPrice(BigDecimal.TEN);
    dto.setProductName("testProductName");
    ProductDto resultOfPost = this.restTemplate
        .postForObject("http://localhost:" + port + "/admin/products", dto, ProductDto.class);

    ProductDto result = this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/products/" + resultOfPost.getId(), ProductDto.class);

    assertEquals(dto.getProductName(), result.getProductName());

    this.restTemplate.delete("http://localhost:" + port + "/admin/products/" + result.getId());

    ProductDto afterDeleteProduct = this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/products/" + result.getId(), ProductDto.class);

    assertNull(afterDeleteProduct.getId());

    SideProductDto sideDto = new SideProductDto();
    sideDto.setPrice(BigDecimal.TEN);
    sideDto.setProductName("testSideProductName");
    SideProductDto resultSideOfPost = this.restTemplate
        .postForObject("http://localhost:" + port + "/admin/sideproducts", sideDto, SideProductDto.class);

    SideProductDto sideResult = this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/sideproducts/" + resultOfPost.getId(), SideProductDto.class);

    assertEquals(sideDto.getProductName(), sideResult.getProductName());

    this.restTemplate.delete("http://localhost:" + port + "/admin/sideproducts/" + sideResult.getId());

    SideProductDto afterDeleteSideProduct = this.restTemplate
        .getForObject("http://localhost:" + port + "/admin/sideproducts/" + sideResult.getId(), SideProductDto.class);

    assertNull(afterDeleteSideProduct.getId());

  }


}