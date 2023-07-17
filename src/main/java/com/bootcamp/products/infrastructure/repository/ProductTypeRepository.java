package com.bootcamp.products.infrastructure.repository;
import com.bootcamp.products.infrastructure.repository.dao.ProductTypeDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Query;
import reactor.core.publisher.Mono;
public interface ProductTypeRepository extends ReactiveMongoRepository<ProductTypeDao,String> {
    @Query(value = "{ 'name': ?0 }", exists = true)
    Mono<Boolean> existsByName(String name);
    @Query(value = "{ 'name': ?0}")
    Mono<ProductTypeDao> findByName(String name);
}
