package com.bootcamp.products.infrastructure.repository.dao;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
