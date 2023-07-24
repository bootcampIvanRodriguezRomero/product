package com.bootcamp.products.infrastructure.rest;

import com.bootcamp.products.application.dto.ProductTypeDto;
import com.bootcamp.products.application.services.ProductTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/product_types")
@RequiredArgsConstructor
public class ProductTypeResource {
  
  private final ProductTypeService productTypeService;
  
  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ProductTypeDto> getAllProductTypes() {
    return productTypeService.getAllProductTypes();
  }
  
  @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<ProductTypeDto> getProductTypeById(@PathVariable String id) {
    return productTypeService.getProductTypeById(id);
  }
  
  @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<ProductTypeDto> createProductType(@Valid @RequestBody ProductTypeDto productTypeDto) {
    return productTypeService.createProductType(productTypeDto);
  }
  
  @PutMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<ProductTypeDto> modifyProductType(@PathVariable String id,
                                        @Valid @RequestBody ProductTypeDto productTypeDto) {
    return productTypeService.modifyProductType(id, productTypeDto);
  }
  
  @DeleteMapping("/{id}")
  public Mono<Void> deleteProductType(String id) {
    return productTypeService.deleteProductType(id);
  }
  
  @DeleteMapping
  public Mono<Void> deleteAllProductTypes() {
    return productTypeService.deleteAllProductTypes();
  }
}
