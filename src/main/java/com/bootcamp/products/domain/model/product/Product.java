package com.bootcamp.products.domain.model.product;

import com.bootcamp.products.domain.model.Client;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Product {
  private String id;
  private ProductType type;
  private boolean isActive;
  private double balance;
  private double interestRate;
  private ProductData data;
  private LocalDateTime created;
  private LocalDateTime updated;
  private List<Client> clients;
}
