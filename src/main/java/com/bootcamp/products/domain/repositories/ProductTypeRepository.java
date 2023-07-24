package com.bootcamp.products.domain.repositories;

import com.bootcamp.products.domain.model.product.ProductType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductTypeRepository {
  Flux<ProductType> findAll();
  
  Mono<ProductType> findById(String id);
  Mono<Boolean> existsByName(String name);
  
  Mono<ProductType> save(ProductType productType);
  
  Mono<Void> deleteById(String id);
  
  Mono<Void> deleteAll();
}
