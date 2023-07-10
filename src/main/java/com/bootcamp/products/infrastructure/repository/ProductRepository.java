package com.bootcamp.products.infrastructure.repository;
import com.bootcamp.products.infrastructure.repository.dao.ProductDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
public interface ProductRepository extends ReactiveMongoRepository<ProductDao,String> {
}
