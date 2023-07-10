package com.bootcamp.products.infrastructure.repository;
import com.bootcamp.products.infrastructure.repository.dao.ClientDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
public interface ClientRepository extends ReactiveMongoRepository<ClientDao,String> {
}
