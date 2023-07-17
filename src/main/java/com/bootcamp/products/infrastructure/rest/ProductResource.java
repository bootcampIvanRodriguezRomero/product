package com.bootcamp.products.infrastructure.rest;

import com.bootcamp.products.infrastructure.repository.ProductRepository;
import com.bootcamp.products.infrastructure.repository.ProductTypeRepository;
import com.bootcamp.products.infrastructure.repository.dao.ProductDao;
import com.bootcamp.products.infrastructure.rest.dto.ProductDto;
import com.bootcamp.products.infrastructure.rest.dto.ProductPostDto;
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
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductResource {
  private final ProductRepository productRepository;
  private final ProductTypeRepository productTypeRepository;
  
  /**
   * Retrieves all products.

   * @return A Flux of ProductDto representing all products
   */
  @GetMapping
  public Flux<ProductDto> getAllProducts() {
    return productRepository.findAll()
      .map(this::fromProductDaoToProductDto);
  }
  
  /**
   * Retrieves a specific product by ID.
   */
  @GetMapping("/{id}")
  public Mono<ProductDto> findProductById(@PathVariable String id) {
    return productRepository.findById(id)
      .map(this::fromProductDaoToProductDto);
  }
  
  /**
   * Creates a new product.

   * @param productPostDto The DTO containing the details of the product to be created
  
   * @return A Mono of ProductDto representing the created product
   */
  @PostMapping
  public Mono<ProductDto> createProduct(@Valid @RequestBody ProductPostDto productPostDto) {
    return productTypeRepository.findByName(productPostDto.getType())
      .flatMap(productTypeDao -> {
        ProductDao productDao = fromProductPostDtoToProductDao(productPostDto);
        productDao.setType(productTypeDao);
        return productRepository.save(productDao)
          .map(this::fromProductDaoToProductDto);
      })
      .switchIfEmpty(Mono.error(
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product type does not exist")));
  }
  
  /**
   * Modifies an existing product.
   */
  @PutMapping("/{id}")
  public Mono<ProductDto> modifyProduct(@PathVariable String id,
                                        @Valid @RequestBody ProductPostDto productPostDto) {
    return productTypeRepository.findByName(productPostDto.getType())
      .flatMap(productTypeDao -> productRepository.findById(id)
        .flatMap(existingProduct -> {
          existingProduct.setType(productTypeDao);
          return productRepository.save(existingProduct);
        })
        .map(this::fromProductDaoToProductDto))
      .switchIfEmpty(Mono.error(
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product type does not exist")));
  }
  
  /**
   * Deletes a product by ID.
   */
  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable String id) {
    log.info("Deleting product with ID: {}", id);
    productRepository.deleteById(id);
    log.info("Product deleted successfully");
  }
  
  /**
   * Deletes all products.
   */
  @DeleteMapping
  public void deleteAllProducts() {
    log.info("Deleting all products");
    productRepository.deleteAll();
    log.info("All products deleted successfully");
  }
  
  private ProductDto fromProductDaoToProductDto(ProductDao productDao) {
    ProductDto productDto = new ProductDto();
    productDto.setId(productDao.getId());
    productDto.setType(productDao.getType());
    return productDto;
  }
  
  private ProductDao fromProductPostDtoToProductDao(ProductPostDto productPostDto) {
    ProductDao productDao = new ProductDao();
    productDao.setId(UUID.randomUUID().toString());
    productDao.setAmount(productDao.getAmount());
    return productDao;
  }
}
