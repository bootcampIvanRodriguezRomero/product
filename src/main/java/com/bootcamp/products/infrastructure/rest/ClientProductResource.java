package com.bootcamp.products.infrastructure.rest;
import com.bootcamp.products.infrastructure.repository.ClientRepository;
import com.bootcamp.products.infrastructure.repository.ProductRepository;
import com.bootcamp.products.infrastructure.repository.dao.ClientDao;
import com.bootcamp.products.infrastructure.repository.dao.ProductDao;
import com.bootcamp.products.infrastructure.rest.dto.ClientDto;
import com.bootcamp.products.infrastructure.rest.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;
@RestController
@RequestMapping("/api/clients/{clientId}/products")
@RequiredArgsConstructor
@Slf4j
public class ClientProductResource {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    @GetMapping
    public Flux<ProductDto> getClientProducts(@PathVariable String clientId) {
        return clientRepository.findById(clientId)
                .flatMapMany(client -> Flux.fromIterable(client.getProductIds()))
                .flatMap(productId -> productRepository.findById(productId))
                .map(this::fromProductDaoToProductDto);
    }

    @GetMapping("/{productId}")
    public Mono<ProductDto> getClientProductById(@PathVariable String clientId, @PathVariable String productId) {
        return productRepository.findById(productId)
                .filter(product -> product.getClientIds().contains(clientId))
                .map(this::fromProductDaoToProductDto);
    }

    @PostMapping("/{productId}")
    public Mono<ClientDto> addProductToClient(@PathVariable String clientId, @PathVariable String productId) {
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

    @DeleteMapping("/{productId}")
    public Mono<ClientDto> removeProductFromClient(@PathVariable String clientId, @PathVariable String productId) {
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
