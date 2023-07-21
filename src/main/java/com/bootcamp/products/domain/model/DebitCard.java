package com.bootcamp.products.domain.model;

import com.bootcamp.products.domain.model.product.Product;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class DebitCard {
  private String id;
  private String cardNumber;
  private LocalDate expirationDate;
  private String cvv;
  private Product mainProduct;
  private List<Product> products;
}
