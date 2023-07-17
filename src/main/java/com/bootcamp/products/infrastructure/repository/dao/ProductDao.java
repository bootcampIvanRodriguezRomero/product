package com.bootcamp.products.infrastructure.repository.dao;

import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a product entity stored in the database.
 */
@Data
@Document("product")
public class ProductDao {
  @Id
  private String id;
  private ProductTypeDao type;
  private double amount;
  private List<String> clientIds;
  private List<String> authorizedSignersIds;
}
