package com.bootcamp.products.infrastructure.dao;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("product_type")
public class ProductTypeDao {
  @Id
  private String id;
  private String name;
  private String description;
  private LocalDateTime created;
  private LocalDateTime updated;
}
