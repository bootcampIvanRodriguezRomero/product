package com.bootcamp.products.application.services;

import com.bootcamp.products.application.dto.ProductTypeDto;
import com.bootcamp.products.application.dto.ProductTypeDtoConverter;
import com.bootcamp.products.domain.model.product.ProductType;
import com.bootcamp.products.domain.repositories.ProductTypeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {
  
  private final ProductTypeRepository productTypeRepository;
  
  @Override
  public Flux<ProductTypeDto> getAllProductTypes() {
    return productTypeRepository.findAll()
      .map(ProductTypeDtoConverter.INSTANCE::productTypeToProductTypeDto);
  }
  
  @Override
  public Mono<ProductTypeDto> getProductTypeById(String id) {
    return productTypeRepository.findById(id)
      .map(ProductTypeDtoConverter.INSTANCE::productTypeToProductTypeDto);
  }
  
  @Override
  public Mono<ProductTypeDto> createProductType(ProductTypeDto productTypeDto) {
    return productTypeRepository.existsByName(productTypeDto.getName())
      .flatMap(nameExists -> {
        if (nameExists) {
          return Mono.error(
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name already exists"));
        } else {
          ProductType productType =
            ProductTypeDtoConverter.INSTANCE.productTypeDtoToProductType(productTypeDto);
          productType.setCreated(LocalDateTime.now());
          productType.setUpdated(LocalDateTime.now());
          return productTypeRepository.save(productType)
            .map(ProductTypeDtoConverter.INSTANCE::productTypeToProductTypeDto);
        }
      });
  }
  
  @Override
  public Mono<ProductTypeDto> modifyProductType(String id, ProductTypeDto productTypeDto) {
    return productTypeRepository.findById(id)
      .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product type not found")))
      .flatMap(existingProductType -> {
        existingProductType.setName(productTypeDto.getName());
        existingProductType.setDescription(productTypeDto.getDescription());
        existingProductType.setUpdated(LocalDateTime.now());
        return productTypeRepository.save(existingProductType)
          .map(ProductTypeDtoConverter.INSTANCE::productTypeToProductTypeDto);
      });
  }
  
  @Override
  public Mono<Void> deleteProductType(String id) {
    return productTypeRepository.deleteById(id);
  }
  
  @Override
  public Mono<Void> deleteAllProductTypes() {
    return productTypeRepository.deleteAll();
  }
}
