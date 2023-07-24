package com.bootcamp.products.application.services;

import com.bootcamp.products.application.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
  Flux<ProductDto> getAllProducts();
  
  Mono<ProductDto> getProductById(String id);
  
  Mono<ProductDto> createProduct(ProductDto productDto);
  
  Mono<ProductDto> modifyProduct(String id, ProductDto productDto);
  
  Mono<Void> deleteProduct(String id);
  
  Mono<Void> deleteAllProducts();
}
