package com.bootcamp.products.infrastructure.rest;

import com.bootcamp.products.application.dto.ProductDto;
import com.bootcamp.products.application.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
@Slf4j
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductResource {
  
  private final ProductService productService;
  
  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ProductDto> getAllProducts() {
    return productService.getAllProducts();
  }
  
  @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<ProductDto> getProductById(@PathVariable String id) {
    return productService.getProductById(id);
  }
  
  @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<ProductDto> createProduct(@RequestBody ProductDto productDto) {
    return productService.createProduct(productDto);
  }
  
  @PutMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<ProductDto> modifyProduct(@PathVariable String id,
                                        @Valid @RequestBody ProductDto productDto) {
    return productService.modifyProduct(id, productDto);
  }
  
  @DeleteMapping("/{id}")
  public Mono<Void> deleteProduct(String id) {
    return productService.deleteProduct(id);
  }
  
  @DeleteMapping
  public Mono<Void> deleteAllProducts() {
    return productService.deleteAllProducts();
  }
}
