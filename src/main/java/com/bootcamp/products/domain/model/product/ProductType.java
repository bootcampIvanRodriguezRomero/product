package com.bootcamp.products.domain.model.product;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProductType {
  private String id;
  private String name;
  private String description;
  private LocalDateTime created;
  private LocalDateTime updated;
}
