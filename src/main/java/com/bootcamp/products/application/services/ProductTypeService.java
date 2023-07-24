package com.bootcamp.products.application.services;

import com.bootcamp.products.application.dto.ProductTypeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductTypeService {
  Flux<ProductTypeDto> getAllProductTypes();
  
  Mono<ProductTypeDto> getProductTypeById(String id);
  
  Mono<ProductTypeDto> createProductType(ProductTypeDto productTypeDto);
  
  Mono<ProductTypeDto> modifyProductType(String id, ProductTypeDto productTypeDto);
  
  Mono<Void> deleteProductType(String id);
  
  Mono<Void> deleteAllProductTypes();
}
