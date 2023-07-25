package com.bootcamp.products.infrastructure.rest;

import com.bootcamp.products.application.dto.ProductDto;
import com.bootcamp.products.application.services.ClientProductService;
import com.bootcamp.products.application.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientProductResource {
  
  private final ClientProductService clientProductService;
  
  @GetMapping("/{id}/products")
  public Flux<ProductDto> getProductsByClientId(@PathVariable String id) {
    return clientProductService.getProductsByClientId(id);
  }
  
  @GetMapping("/{id}/products/{productId}")
  public Mono<ProductDto> getProductByClientIdAndProductId(@PathVariable String id, @PathVariable String productId) {
    return clientProductService.getProductByClientIdAndProductId(id, productId);
  }
  @PostMapping("/{id}/products")
  public Mono<ProductDto> addProductToClient(@PathVariable String id, @RequestBody ProductDto productDto) {
    return clientProductService.addProductToClient(id, productDto);
  }
  
  @PutMapping("/{id}/products/{productId}")
  public Mono<ProductDto> updateProductForClient(@PathVariable String id, @PathVariable String productId, @RequestBody ProductDto productDto) {
    return clientProductService.updateProductForClient(id, productId, productDto);
  }
  
  @DeleteMapping("/{id}/products/{productId}")
  public Mono<Void> removeProductFromClient(@PathVariable String id, @PathVariable String productId) {
    return clientProductService.removeProductFromClient(id, productId);
  }
  @DeleteMapping("/{id}/products")
  public Mono<Void> removeAllProductsFromClient(@PathVariable String id) {
    return clientProductService.removeAllProductsFromClient(id);
  }
}

