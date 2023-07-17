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

@RestController
@RequestMapping("/api/company-pyme")
@RequiredArgsConstructor
@Slf4j
public class CompanyPymeProductResource {
  private final ProductRepository productRepository;
  private final ProductTypeRepository productTypeRepository;
  @GetMapping("/{id}")
  public Mono<ProductDto> findCompanyPymeProductById(@PathVariable String id) {
    return productRepository.findById(id)
      .filter(product -> product.getType().getName().equals("VIP"))
      .map(this::fromProductDaoToProductDto);
  }
  
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
      .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product type does not exist or the product is not of type  'VIP'")));
  }
  
  @DeleteMapping("/{id}")
  public void deleteCompanyPymeProduct(@PathVariable String id) {
    productRepository.deleteById(id);
  }
  private ProductDto fromProductDaoToProductDto(ProductDao productDao) {
    ProductDto productDto = new ProductDto();
    productDto.setId(productDao.getId());
    productDto.setType(productDao.getType());
    return productDto;
  }
}
