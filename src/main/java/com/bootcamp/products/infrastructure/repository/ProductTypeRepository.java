package com.bootcamp.products.infrastructure.repository;

import com.bootcamp.products.infrastructure.repository.dao.ProductTypeDao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Repository interface for managing product types in the database.
 */
public interface ProductTypeRepository extends ReactiveMongoRepository<ProductTypeDao, String> {
  
  /**
   * Checks if a product type with the given name exists.
   */
  @Query(value = "{ 'name': ?0 }", exists = true)
  Mono<Boolean> existsByName(String name);
  
  /**
   * Retrieves a product type by its name.
   */
  @Query(value = "{ 'name': ?0}")
  Mono<ProductTypeDao> findByName(String name);
}
