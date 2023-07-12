package com.bootcamp.products.infrastructure.rest;
import com.bootcamp.products.infrastructure.repository.ProductTypeRepository;
import com.bootcamp.products.infrastructure.repository.dao.ProductTypeDao;
import com.bootcamp.products.infrastructure.rest.dto.ProductTypeDto;
import com.bootcamp.products.infrastructure.rest.dto.ProductTypePostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;
@RestController
@RequestMapping("/productType")
@RequiredArgsConstructor
@Slf4j
public class ProductTypeResource {
    private final ProductTypeRepository productTypeRepository;
    @GetMapping
    public Flux<ProductTypeDto> getAllProductTypes() {
        return productTypeRepository.findAll()
                .map(this::fromProductTypeDaoToProductTypeDto);
    }
    @GetMapping("/{id}")
    public Mono<ProductTypeDto> findProductTypeById(@PathVariable String id) {
        return productTypeRepository.findById(id)
                .map(this::fromProductTypeDaoToProductTypeDto);
    }
    @PostMapping
    public Mono<ProductTypeDto> createProductType(@Valid @RequestBody ProductTypePostDto productTypePostDto) {
        return productTypeRepository.existsByName(productTypePostDto.getName())
               .flatMap(nameExists -> {
                    if (nameExists) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name already exists"));
                    } else {
                        ProductTypeDao productTypeDao = fromProductTypePostToProductTypeDao(productTypePostDto);
                        return productTypeRepository.save(productTypeDao)
                                .map(this::fromProductTypeDaoToProductTypeDto);
                    }
               });
    }
    @PutMapping("/{id}")
    public Mono<ProductTypeDto> modifyProductType(@PathVariable String id, @Valid @RequestBody ProductTypePostDto productTypePostDto) {
        return  productTypeRepository.existsByName(productTypePostDto.getName())
                .flatMap( nameExists -> {
                    if(nameExists) {
                        return  Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name already exists"));
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

    @DeleteMapping("/{id}")
    public void deleteProductType(@PathVariable String id) {
        log.info("Deleting type of product with ID: {}", id);
        productTypeRepository.deleteById(id);
        log.info("Type of product deleted successfully");
    }
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

    private ProductTypeDao fromProductTypePostToProductTypeDao(ProductTypePostDto productTypePostDto) {
        ProductTypeDao productTypeDao = new ProductTypeDao();
        productTypeDao.setId(UUID.randomUUID().toString());
        productTypeDao.setName(productTypePostDto.getName());
        return productTypeDao;
    }
}
