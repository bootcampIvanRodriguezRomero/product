package com.bootcamp.products.infrastructure.dao;

import com.bootcamp.products.domain.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductDaoConverter {
  ProductDaoConverter INSTANCE = Mappers.getMapper(ProductDaoConverter.class);
  
  @Mapping(target = "id", source = "product.id")
  @Mapping(target = "type", source = "product.type")
  @Mapping(target = "isActive", source = "product.isActive")
  @Mapping(target = "balance", source = "product.balance")
  @Mapping(target = "interestRate", source = "product.interestRate")
  @Mapping(target = "data", source = "product.data")
  @Mapping(target = "created", source = "product.created")
  @Mapping(target = "updated", source = "product.updated")
  @Mapping(target = "clients", source = "product.clients")
  ProductDao productToProductDao(Product product);
  
  @Mapping(target = "id", source = "productDao.id")
  @Mapping(target = "type", source = "productDao.type")
  @Mapping(target = "isActive", source = "productDao.isActive")
  @Mapping(target = "balance", source = "productDao.balance")
  @Mapping(target = "interestRate", source = "productDao.interestRate")
  @Mapping(target = "data", source = "productDao.data")
  @Mapping(target = "created", source = "productDao.created")
  @Mapping(target = "updated", source = "productDao.updated")
  @Mapping(target = "clients", source = "productDao.clients")
  Product productDaoToProduct(ProductDao productDao);
}
