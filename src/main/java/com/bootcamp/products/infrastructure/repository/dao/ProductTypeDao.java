package com.bootcamp.products.infrastructure.repository.dao;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("productType")
public class ProductTypeDao {
    @Id
    private String id;
    private String name;
}
