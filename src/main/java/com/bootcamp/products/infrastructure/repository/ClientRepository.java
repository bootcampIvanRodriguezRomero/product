package com.bootcamp.products.infrastructure.repository;

import com.bootcamp.products.infrastructure.repository.dao.ClientDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Repository interface for managing clients.
 * Provides CRUD operations and query capabilities for the Client entity.
 */
public interface ClientRepository extends ReactiveMongoRepository<ClientDao, String> {
}
