package com.bootcamp.products.application.services;

import com.bootcamp.products.application.dto.ProductDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientProductServiceImpl implements ClientProductService {
  
  private final ClientService clientService;
  private final ProductService productService;
  
  @Override
  public Flux<ProductDto> getProductsByClientId(String clientId) {
    return clientService.getClientById(clientId)
      .flatMapMany(clientDto -> {
        Flux<ProductDto> products = clientDto.getProducts();
        return products;
      });
  }
  
  @Override
  public Mono<ProductDto> getProductByClientIdAndProductId(String clientId, String productId) {
    return clientService.getClientById(clientId)
      .flatMap(clientDto -> {
        return clientDto.getProducts()
          .filter(product -> product.getId().equals(productId))
          .next()
          .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for the given client")));
      });
  }
  
  @Override
  public Mono<ProductDto> addProductToClient(String clientId, ProductDto productDto) {
    return clientService.getClientById(clientId)
      .flatMap(clientDto -> {
        return clientDto.getProducts()
          .filter(existingProduct -> existingProduct.getId().equals(productDto.getId()))
          .hasElements()
          .flatMap(productExists -> {
            if (productExists) {
              return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists for the given client"));
            } else {
              clientDto.getProducts().concatWith(Mono.just(productDto));
              return clientService.modifyClient(clientId, clientDto)
                .thenReturn(productDto);
            }
          });
      });
  }
  
  @Override
  public Mono<ProductDto> updateProductForClient(String clientId, String productId, ProductDto productDto) {
    return clientService.getClientById(clientId)
      .flatMap(clientDto -> {
        Flux<ProductDto> products = clientDto.getProducts();
        return products
          .filter(product -> product.getId().equals(productId))
          .next()
          .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for the given client")))
          .flatMap(existingProduct -> {
            return productService.modifyProduct(productId, productDto);
          });
      });
  }
  
  @Override
  public Mono<Void> removeProductFromClient(String clientId, String productId) {
    return clientService.getClientById(clientId)
      .flatMap(clientDto -> {
        Flux<ProductDto> products = clientDto.getProducts();
        return products
          .filter(product -> product.getId().equals(productId))
          .next()
          .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for the given client")))
          .flatMap(existingProduct -> {
            Flux<ProductDto> updatedProducts = products.filter(product -> !product.getId().equals(productId));
            return clientService.modifyClient(clientId, clientDto.withProducts(updatedProducts));
          })
          .then();
      });
  }
  
  @Override
  public Mono<Void> removeAllProductsFromClient(String clientId) {
    return clientService.getClientById(clientId)
      .flatMap(clientDto -> {
        Flux<ProductDto> emptyProducts = Flux.empty();
        clientDto.setProducts(emptyProducts);
        return clientService.modifyClient(clientId, clientDto)
          .then();
      });
  }



}

