package com.bootcamp.products.application.services;

import com.bootcamp.products.application.dto.ProductDto;
import com.bootcamp.products.application.dto.ProductDtoConverter;
import com.bootcamp.products.domain.model.product.Product;
import com.bootcamp.products.domain.repositories.ProductRepository;
import com.bootcamp.products.domain.repositories.ProductTypeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ProductTypeRepository productTypeRepository;
  @Override
  public Flux<ProductDto> getAllProducts() {
    return productRepository.findAll()
      .map(ProductDtoConverter.INSTANCE::productToProductDto);
  }
  
  @Override
  public Mono<ProductDto> getProductById(String id) {
    return productRepository.findById(id)
      .map(ProductDtoConverter.INSTANCE::productToProductDto);
  }
  
  @Override
  public Mono<ProductDto> createProduct(ProductDto productDto) {
    return productTypeRepository.findById(productDto.getType().getId())
      .switchIfEmpty(Mono.error(
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product type does not exist")))
      .flatMap(productType -> {
        Product product = ProductDtoConverter.INSTANCE.productDtoToProduct(productDto);
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        return productRepository.save(product)
          .map(ProductDtoConverter.INSTANCE::productToProductDto);
      });
  }
  
  @Override
  public Mono<ProductDto> modifyProduct(String id, ProductDto productDto) {
    return productRepository.findById(productDto.getId())
      .switchIfEmpty(Mono.error(
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product does not exist")))
      .flatMap(existingProduct -> {
            existingProduct.setType(productDto.getType());
            existingProduct.setIsActive(productDto.getIsActive());
            existingProduct.setBalance(productDto.getBalance());
            existingProduct.setInterestRate(productDto.getInterestRate());
            existingProduct.setData(productDto.getData());
            existingProduct.setUpdated(LocalDateTime.now());
            return productRepository.save(existingProduct);
      })
      .map(ProductDtoConverter.INSTANCE::productToProductDto);
  }
  
  @Override
  public Mono<Void> deleteProduct(String id) {
    return productRepository.deleteById(id);
  }
  
  @Override
  public Mono<Void> deleteAllProducts() {
    return productRepository.deleteAll();
  }
}
