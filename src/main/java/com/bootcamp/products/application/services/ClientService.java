package com.bootcamp.products.application.services;

import com.bootcamp.products.application.dto.ClientDto;
import com.bootcamp.products.application.dto.ClientPostDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
  
  Flux<ClientDto> getAllClients();
  
  Mono<ClientDto> getClientById(String clientId);
  
  Mono<ClientDto> createClient(ClientPostDto clientPostDto);
  
  Mono<ClientDto> modifyClient(String clientId, ClientDto clientDto);
  
  Mono<Void> deleteClient(String clientId);
  
  Mono<Void> deleteAllClients();
}

