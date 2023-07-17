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
 * REST controller for handling Personal VIP product operations.
 */
@RestController
@RequestMapping("/api/personal-vip")
@RequiredArgsConstructor
@Slf4j
public class PersonalVipProductResource {
  private final ProductRepository productRepository;
  private final ProductTypeRepository productTypeRepository;
  
  /**
   * Retrieves a Personal VIP product by ID.
   *
   * @param id The ID of the product.
   * @return A Mono emitting the ProductDto of the Personal VIP product if found, otherwise empty.
   */
  @GetMapping("/{id}")
  public Mono<ProductDto> findPersonalVipProductById(@PathVariable String id) {
    return productRepository.findById(id)
      .filter(product -> product.getType().getName().equals("VIP"))
      .map(this::fromProductDaoToProductDto);
  }
  
  /**
   * Modifies a Personal VIP product.
   *
   * @param id              The ID of the product to modify.
   * @param productPostDto  The updated product data.
   * @return A Mono emitting the modified ProductDto of the Personal VIP product if successful.
   * @throws ResponseStatusException if the provided product type is not 'VIP' or the
   *     opening amount is below 500.
   */
  @PutMapping("/{id}")
  public Mono<ProductDto> modifyPersonalVipProduct(@PathVariable String id, @Valid @RequestBody
      ProductPostDto productPostDto) {
    return productTypeRepository.findByName(productPostDto.getType())
      .flatMap(productTypeDao -> productRepository.findById(id)
        .filter(product -> product.getType().getName().equals("VIP"))
        .flatMap(existingProduct -> {
          if (productPostDto.getAmount() < 500) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
              "Minimum opening amount for Personal VIP account is 500"));
          }
          existingProduct.setType(productTypeDao);
          existingProduct.setAmount(productPostDto.getAmount());
          return productRepository.save(existingProduct);
        })
        .map(this::fromProductDaoToProductDto))
      .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
        "The product type does not exist or the product is not of type 'VIP'")));
  }
  
  /**
   * Deletes a Personal VIP product by ID.
   *
   * @param id The ID of the product to delete.
   */
  @DeleteMapping("/{id}")
  public void deletePersonalVipProduct(@PathVariable String id) {
    log.info("Deleting Personal VIP product with ID: {}", id);
    productRepository.deleteById(id);
    log.info("Personal VIP product deleted successfully");
  }
  
  private ProductDto fromProductDaoToProductDto(ProductDao productDao) {
    ProductDto productDto = new ProductDto();
    productDto.setId(productDao.getId());
    productDto.setType(productDao.getType());
    return productDto;
  }
  
}
