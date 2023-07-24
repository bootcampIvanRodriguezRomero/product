package com.bootcamp.products.domain.repositories;

import com.bootcamp.products.domain.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
  Flux<Product> findAll();
  
  Mono<Product> findById(String id);
  
  Mono<Product> save(Product product);
  
  Mono<Void> deleteById(String id);
  
  Mono<Void> deleteAll();
}
