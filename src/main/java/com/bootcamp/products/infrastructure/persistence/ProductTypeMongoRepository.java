package com.bootcamp.products.infrastructure.persistence;

import com.bootcamp.products.infrastructure.dao.ProductTypeDao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductTypeMongoRepository extends ReactiveMongoRepository<ProductTypeDao, String> {
  @Query(value = "{'name':?0}", exists = true)
  Mono<Boolean> existsByName(String name);
}
