package com.bootcamp.products.infrastructure.rest;

import com.bootcamp.products.infrastructure.repository.ProductTypeRepository;
import com.bootcamp.products.infrastructure.repository.dao.ProductTypeDao;
import com.bootcamp.products.infrastructure.rest.dto.ProductTypeDto;
import com.bootcamp.products.infrastructure.rest.dto.ProductTypePostDto;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing product types.
 */
@RestController
@RequestMapping("/productType")
@RequiredArgsConstructor
@Slf4j
public class ProductTypeResource {
  private final ProductTypeRepository productTypeRepository;
  
  /**
   * Retrieves all product types.

   * @return A Flux of ProductTypeDto representing all product types
   */
  @GetMapping
  public Flux<ProductTypeDto> getAllProductTypes() {
    return productTypeRepository.findAll()
      .map(this::fromProductTypeDaoToProductTypeDto);
  }
  
  /**
   * Retrieves a specific product type by ID.

   * @param id The ID of the product type
 
   * @return A Mono of ProductTypeDto representing the product type
   */
  @GetMapping("/{id}")
  public Mono<ProductTypeDto> findProductTypeById(@PathVariable String id) {
    return productTypeRepository.findById(id)
      .map(this::fromProductTypeDaoToProductTypeDto);
  }
  
  /**
   * Creates a new product type.

   * @param productTypePostDto The DTO containing the details of the product type to be created
  
   * @return A Mono of ProductTypeDto representing the created product type
   */
  @PostMapping
  public Mono<ProductTypeDto> createProductType(
      @Valid @RequestBody ProductTypePostDto productTypePostDto) {
    return productTypeRepository.existsByName(productTypePostDto.getName())
      .flatMap(nameExists -> {
        if (nameExists) {
          return Mono.error(
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name already exists"));
        } else {
          ProductTypeDao productTypeDao =
              fromProductTypePostToProductTypeDao(productTypePostDto);
          return productTypeRepository.save(productTypeDao)
            .map(this::fromProductTypeDaoToProductTypeDto);
        }
      });
  }
  
  /**
   * Modifies an existing product type.

   * @param id The ID of the product type to be modified
 
   * @param productTypePostDto The DTO containing the updated details of the product type
  
   * @return A Mono of ProductTypeDto representing the modified product type
   */
  @PutMapping("/{id}")
  public Mono<ProductTypeDto> modifyProductType(@PathVariable String id, @Valid @RequestBody
      ProductTypePostDto productTypePostDto) {
    return productTypeRepository.existsByName(productTypePostDto.getName())
      .flatMap(nameExists -> {
        if (nameExists) {
          return Mono.error(
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name already exists"));
        } else {
          return productTypeRepository.findById(id)
            .flatMap(existingProduct -> {
              existingProduct.setName(productTypePostDto.getName());
              return productTypeRepository.save(existingProduct);
            })
            .map(this::fromProductTypeDaoToProductTypeDto);
        }
      });
  }
  
  /**
   * Deletes a product type by ID.

   * @param id The ID of the product type to be deleted
   */
  @DeleteMapping("/{id}")
  public void deleteProductType(@PathVariable String id) {
    log.info("Deleting type of product with ID: {}", id);
    productTypeRepository.deleteById(id);
    log.info("Type of product deleted successfully");
  }
  
  /**
   * Deletes all product types.
   */
  @DeleteMapping
  public void deleteAllProductTypes() {
    log.info("Deleting all type of products");
    productTypeRepository.deleteAll();
    log.info("All type of products deleted successfully");
  }
  
  private ProductTypeDto fromProductTypeDaoToProductTypeDto(ProductTypeDao productTypeDao) {
    ProductTypeDto productTypeDto = new ProductTypeDto();
    productTypeDto.setId(productTypeDao.getId());
    productTypeDto.setName(productTypeDao.getName());
    return productTypeDto;
  }
  
  private ProductTypeDao fromProductTypePostToProductTypeDao(
      ProductTypePostDto productTypePostDto) {
    ProductTypeDao productTypeDao = new ProductTypeDao();
    productTypeDao.setId(UUID.randomUUID().toString());
    productTypeDao.setName(productTypePostDto.getName());
    return productTypeDao;
  }
}
