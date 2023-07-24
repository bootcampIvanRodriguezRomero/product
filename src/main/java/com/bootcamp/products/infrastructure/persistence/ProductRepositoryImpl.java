package com.bootcamp.products.infrastructure.persistence;

import com.bootcamp.products.domain.model.product.Product;
import com.bootcamp.products.domain.repositories.ProductRepository;
import com.bootcamp.products.infrastructure.dao.ProductDao;
import com.bootcamp.products.infrastructure.dao.ProductDaoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductMongoRepository productMongoRepository;
  @Override
  public Flux<Product> findAll() {
    return productMongoRepository.findAll()
      .map(ProductDaoConverter.INSTANCE::productDaoToProduct);
  }
  
  @Override
  public Mono<Product> findById(String id) {
    return productMongoRepository.findById(id)
      .map(ProductDaoConverter.INSTANCE::productDaoToProduct);
  }
  
  @Override
  public Mono<Product> save(Product product) {
    ProductDao productDao = ProductDaoConverter.INSTANCE.productToProductDao(product);
    return productMongoRepository.save(productDao)
      .map(ProductDaoConverter.INSTANCE::productDaoToProduct);
  }
  
  @Override
  public Mono<Void> deleteById(String id) {
    return productMongoRepository.deleteById(id);
  }
  
  @Override
  public Mono<Void> deleteAll() {
    return productMongoRepository.deleteAll();
  }
}
