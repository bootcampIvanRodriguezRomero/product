package com.bootcamp.products.infrastructure.repository;

import com.bootcamp.products.infrastructure.repository.dao.ProductDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Repository interface for managing products in the database.
 */
public interface ProductRepository extends ReactiveMongoRepository<ProductDao, String> {
}
