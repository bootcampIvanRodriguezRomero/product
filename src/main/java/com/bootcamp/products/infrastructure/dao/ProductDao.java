package com.bootcamp.products.infrastructure.dao;

import com.bootcamp.products.domain.model.Client;
import com.bootcamp.products.domain.model.product.ProductData;
import com.bootcamp.products.domain.model.product.ProductType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("product")
public class ProductDao {
  @Id
  private String id;
  private ProductType type;
  private Boolean isActive;
  private Double balance;
  private Double interestRate;
  private ProductData data;
  @CreatedDate
  private LocalDateTime created;
  @LastModifiedDate
  private LocalDateTime updated;
  private List<Client> clients;
  
}
