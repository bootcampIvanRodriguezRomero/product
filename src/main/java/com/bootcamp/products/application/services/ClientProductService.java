package com.bootcamp.products.application.services;

import com.bootcamp.products.application.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientProductService {
  
  Flux<ProductDto> getProductsByClientId(String clientId);
  
  Mono<ProductDto> getProductByClientIdAndProductId(String clientId, String productId);
  
  Mono<ProductDto> addProductToClient(String clientId, ProductDto productDto);
  
  Mono<ProductDto> updateProductForClient(String clientId, String productId, ProductDto productDto);
  
  Mono<Void> removeProductFromClient(String clientId, String productId);
  
  Mono<Void> removeAllProductsFromClient(String clientId);
}

