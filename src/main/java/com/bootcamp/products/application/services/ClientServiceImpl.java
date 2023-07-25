package com.bootcamp.products.application.services;

import com.bootcamp.products.application.dto.ClientDto;
import com.bootcamp.products.application.dto.ClientPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
  
  private final WebClient.Builder webClientBuilder;
  
  @Override
  public Flux<ClientDto> getAllClients() {
    return webClientBuilder.build()
      .get()
      .uri("http://client/clients")
      .retrieve()
      .bodyToFlux(ClientDto.class);
  }
  
  @Override
  public Mono<ClientDto> getClientById(String clientId) {
    return webClientBuilder.build()
      .get()
      .uri("http://client/clients/{id}", clientId)
      .retrieve()
      .bodyToMono(ClientDto.class);
  }
  
  @Override
  public Mono<ClientDto> createClient(ClientPostDto clientPostDto) {
    return webClientBuilder.build()
      .post()
      .uri("http://client/clients")
      .bodyValue(clientPostDto)
      .retrieve()
      .bodyToMono(ClientDto.class);
  }
  
  @Override
  public Mono<ClientDto> modifyClient(String clientId, ClientDto clientDto) {
    return webClientBuilder.build()
      .put()
      .uri("http://client/clients/{id}", clientId)
      .bodyValue(clientDto)
      .retrieve()
      .bodyToMono(ClientDto.class);
  }
  
  @Override
  public Mono<Void> deleteClient(String clientId) {
    return webClientBuilder.build()
      .delete()
      .uri("http://client/clients/{id}", clientId)
      .retrieve()
      .bodyToMono(Void.class);
  }
  
  @Override
  public Mono<Void> deleteAllClients() {
    return webClientBuilder.build()
      .delete()
      .uri("http://client/clients")
      .retrieve()
      .bodyToMono(Void.class);
  }
}

