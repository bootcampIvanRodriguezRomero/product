package com.bootcamp.products.infrastructure.persistence;

import com.bootcamp.products.infrastructure.dao.ProductDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductMongoRepository extends ReactiveMongoRepository<ProductDao, String> {
}
