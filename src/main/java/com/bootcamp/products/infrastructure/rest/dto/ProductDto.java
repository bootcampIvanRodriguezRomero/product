package com.bootcamp.products.infrastructure.rest.dto;

import com.bootcamp.products.infrastructure.repository.dao.ProductTypeDao;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Data Transfer Object (DTO) class representing a product entity.
 */
@Data
public class ProductDto {
  @JsonProperty("identification")
  @Id
  private String id;
  private ProductTypeDao type;
  private double amount;
  private List<String> clientIds;
  private List<String> authorizedSignersIds;
}
