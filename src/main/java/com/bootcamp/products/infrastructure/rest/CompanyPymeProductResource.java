package com.bootcamp.products.infrastructure.rest;

import com.bootcamp.products.infrastructure.repository.ProductRepository;
import com.bootcamp.products.infrastructure.repository.ProductTypeRepository;
import com.bootcamp.products.infrastructure.repository.dao.ProductDao;
import com.bootcamp.products.infrastructure.rest.dto.ProductDto;
import com.bootcamp.products.infrastructure.rest.dto.ProductPostDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * RestController for managing company-pyme products.
 */
@RestController
@RequestMapping("/api/company-pyme")
@RequiredArgsConstructor
@Slf4j
public class CompanyPymeProductResource {
  private final ProductRepository productRepository;
  private final ProductTypeRepository productTypeRepository;
  
  /**
   * Retrieves a company pyme product by its ID.
   *
   * @param id the ID of the product
   * @return a Mono containing the product DTO if found, or an empty Mono if not found
   */
  @GetMapping("/{id}")
  public Mono<ProductDto> findCompanyPymeProductById(@PathVariable String id) {
    return productRepository.findById(id)
      .filter(product -> product.getType().getName().equals("VIP"))
      .map(this::fromProductDaoToProductDto);
  }
  
  /**
   * Modifies a company pyme product.
   *
   * @param id               the ID of the product to modify
   * @param productPostDto   the modified product data
   * @return a Mono containing the modified product DTO
   *     if the modification is successful, or an error Mono if not
   */
  @PutMapping("/{id}")
  public Mono<ProductDto> modifyCompanyPymeProduct(@PathVariable String id, @Valid @RequestBody
      ProductPostDto productPostDto) {
    return productTypeRepository.findByName(productPostDto.getType())
      .flatMap(productTypeDao -> productRepository.findById(id)
        .filter(product -> product.getType().getName().equals("VIP"))
        .flatMap(existingProduct -> {
          existingProduct.setType(productTypeDao);
          return productRepository.save(existingProduct);
        })
        .map(this::fromProductDaoToProductDto))
      .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
        "The product type does not exist or the product is not of type  'VIP'")));
  }
  
  /**
   * Deletes a company pyme product by its ID.
   *
   * @param id the ID of the product to delete
   */
  @DeleteMapping("/{id}")
  public void deleteCompanyPymeProduct(@PathVariable String id) {
    productRepository.deleteById(id);
  }
  
  /**
   * Converts a ProductDao object to a ProductDto object.
   *
   * @param productDao the ProductDao object to convert
   * @return the converted ProductDto object
   */
  private ProductDto fromProductDaoToProductDto(ProductDao productDao) {
    ProductDto productDto = new ProductDto();
    productDto.setId(productDao.getId());
    productDto.setType(productDao.getType());
    return productDto;
  }
}
