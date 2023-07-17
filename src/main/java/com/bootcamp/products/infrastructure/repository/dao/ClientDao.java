package com.bootcamp.products.infrastructure.repository.dao;

import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Data Access Object (DAO) class representing a client entity.
 */
@Data
@Document("client")
public class ClientDao {
  @Id
  private String id;
  private String documentType;
  private String documentNumber;
  private String fullName;
  private String email;
  private String type;
  private List<String> productIds;
}