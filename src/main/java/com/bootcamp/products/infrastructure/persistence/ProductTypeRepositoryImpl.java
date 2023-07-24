package com.bootcamp.products.infrastructure.persistence;

import com.bootcamp.products.domain.model.product.ProductType;
import com.bootcamp.products.domain.repositories.ProductTypeRepository;
import com.bootcamp.products.infrastructure.dao.ProductTypeDao;
import com.bootcamp.products.infrastructure.dao.ProductTypeDaoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductTypeRepositoryImpl implements ProductTypeRepository {
  
  private final ProductTypeMongoRepository productTypeMongoRepository;
  
  @Override
  public Flux<ProductType> findAll() {
    return productTypeMongoRepository.findAll()
      .map(ProductTypeDaoConverter.INSTANCE::productTypeDaoToProductType);
  }
  
  @Override
  public Mono<ProductType> findById(String id) {
    return productTypeMongoRepository.findById(id)
      .map(ProductTypeDaoConverter.INSTANCE::productTypeDaoToProductType);
  }
  @Override
  public Mono<Boolean> existsByName(String name) {
    return productTypeMongoRepository.existsByName(name);
  }
  
  @Override
  public Mono<ProductType> save(ProductType productType) {
    ProductTypeDao productTypeDao =
        ProductTypeDaoConverter.INSTANCE.productTypeToProductTypeDao(productType);
    return productTypeMongoRepository.save(productTypeDao)
      .map(ProductTypeDaoConverter.INSTANCE::productTypeDaoToProductType);
  }
  
  @Override
  public Mono<Void> deleteById(String id) {
    return productTypeMongoRepository.deleteById(id);
  }
  
  @Override
  public Mono<Void> deleteAll() {
    return productTypeMongoRepository.deleteAll();
  }
}
