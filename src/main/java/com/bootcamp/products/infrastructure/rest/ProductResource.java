package com.bootcamp.products.infrastructure.rest;
import com.bootcamp.products.infrastructure.repository.ProductRepository;
import com.bootcamp.products.infrastructure.repository.ProductTypeRepository;
import com.bootcamp.products.infrastructure.repository.dao.ProductDao;
import com.bootcamp.products.infrastructure.rest.dto.ProductDto;
import com.bootcamp.products.infrastructure.rest.dto.ProductPostDto;
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
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductResource {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    @GetMapping
    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .map(this::fromProductDaoToProductDto);
    }
    @GetMapping("/{id}")
    public Mono<ProductDto> findProductById(@PathVariable String id) {
        return productRepository.findById(id)
                .map(this::fromProductDaoToProductDto);
    }
    @PostMapping
    public Mono<ProductDto> createProduct(@Valid @RequestBody ProductPostDto productPostDto) {
        return productTypeRepository.findByName(productPostDto.getType())
                .flatMap(productTypeDao -> {
                    ProductDao productDao = fromProductPostDtoToProductDao(productPostDto);
                    productDao.setType(productTypeDao);
                    return productRepository.save(productDao)
                            .map(this::fromProductDaoToProductDto);
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product type does not exist")));
    }
    @PutMapping("/{id}")
    public Mono<ProductDto> modifyProduct(@PathVariable String id, @Valid @RequestBody ProductPostDto productPostDto) {
        return productTypeRepository.findByName(productPostDto.getType())
                .flatMap(productTypeDao -> productRepository.findById(id)
                        .flatMap(existingProduct -> {
                            existingProduct.setType(productTypeDao);
                            return productRepository.save(existingProduct);
                        })
                        .map(this::fromProductDaoToProductDto))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product type does not exist")));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        log.info("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
        log.info("Product deleted successfully");
    }
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
        return productDao;
    }
}
