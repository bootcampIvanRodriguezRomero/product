package com.bootcamp.products.infrastructure.rest;

import com.bootcamp.products.infrastructure.repository.ClientRepository;
import com.bootcamp.products.infrastructure.repository.ProductRepository;
import com.bootcamp.products.infrastructure.repository.dao.ClientDao;
import com.bootcamp.products.infrastructure.repository.dao.ProductDao;
import com.bootcamp.products.infrastructure.rest.dto.ClientDto;
import com.bootcamp.products.infrastructure.rest.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller to manage client's products.
 */
@RestController
@RequestMapping("/api/clients/{clientId}/products")
@RequiredArgsConstructor
@Slf4j
public class ClientProductResource {
  private final ProductRepository productRepository;
  private final ClientRepository clientRepository;
  
  /**
   * Get all products associated with a client.
   *
   * @param clientId the ID of the client
   * @return a Flux of ProductDto representing the client's products
   */
  @GetMapping
  public Flux<ProductDto> getClientProducts(@PathVariable String clientId) {
    return clientRepository.findById(clientId)
      .flatMapMany(client -> Flux.fromIterable(client.getProductIds()))
      .flatMap(productId -> productRepository.findById(productId))
      .map(this::fromProductDaoToProductDto);
  }
  
  /**
   * Retrieves a specific product associated with a client.
   *
   * @param clientId  the ID of the client
   * @param productId the ID of the product
   * @return a Mono of ProductDto representing the client's product
   */
  @GetMapping("/{productId}")
  public Mono<ProductDto> getClientProductById(@PathVariable String clientId,
                                               @PathVariable String productId) {
    return productRepository.findById(productId)
      .filter(product -> product.getClientIds().contains(clientId))
      .map(this::fromProductDaoToProductDto);
  }
  
  /**
   * Adds a product to a client's list of products.
   *
   * @param clientId  the ID of the client
   * @param productId the ID of the product
   * @return a Mono of ClientDto representing the updated client
   */
  @PostMapping("/{productId}")
  public Mono<ClientDto> addProductToClient(@PathVariable String clientId,
                                            @PathVariable String productId) {
    return Mono.zip(clientRepository.findById(clientId), productRepository.findById(productId))
      .flatMap(tuple -> {
        ClientDao client = tuple.getT1();
        ProductDao product = tuple.getT2();
        
        client.getProductIds().add(product.getId());
        product.getClientIds().add(client.getId());
        
        return Mono.zip(clientRepository.save(client), productRepository.save(product))
          .map(savedTuple -> fromClientDaoToClientDto(savedTuple.getT1()));
      });
  }
  
  /**
   * Removes a product from a client's list of products.
   *
   * @param clientId  the ID of the client
   * @param productId the ID of the product
   * @return a Mono of ClientDto representing the updated client
   */
  @DeleteMapping("/{productId}")
  public Mono<ClientDto> removeProductFromClient(@PathVariable String clientId,
                                                 @PathVariable String productId) {
    return Mono.zip(clientRepository.findById(clientId), productRepository.findById(productId))
      .flatMap(tuple -> {
        ClientDao client = tuple.getT1();
        ProductDao product = tuple.getT2();
        
        client.getProductIds().remove(product.getId());
        product.getClientIds().remove(client.getId());
        
        return Mono.zip(clientRepository.save(client), productRepository.save(product))
          .map(savedTuple -> fromClientDaoToClientDto(savedTuple.getT1()));
      });
  }
  
  private ProductDto fromProductDaoToProductDto(ProductDao productDao) {
    ProductDto productDto = new ProductDto();
    productDto.setId(productDao.getId());
    productDto.setType(productDao.getType());
    return productDto;
  }
  
  private ClientDto fromClientDaoToClientDto(ClientDao clientDao) {
    ClientDto clientDto = new ClientDto();
    clientDto.setId(clientDao.getId());
    clientDto.setDocumentType(clientDao.getDocumentType());
    clientDto.setDocumentNumber(clientDao.getDocumentNumber());
    clientDto.setFullName(clientDao.getFullName());
    clientDto.setEmail(clientDao.getEmail());
    clientDto.setType(clientDao.getType());
    return clientDto;
  }
  
}
