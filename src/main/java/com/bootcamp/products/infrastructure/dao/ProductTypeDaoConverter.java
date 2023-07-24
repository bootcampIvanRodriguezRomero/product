package com.bootcamp.products.infrastructure.dao;

import com.bootcamp.products.domain.model.product.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductTypeDaoConverter {
  ProductTypeDaoConverter INSTANCE = Mappers.getMapper(ProductTypeDaoConverter.class);
  
  @Mapping(target = "id", source = "productTypeDao.id")
  @Mapping(target = "name", source = "productTypeDao.name")
  @Mapping(target = "description", source = "productTypeDao.description")
  @Mapping(target = "created", source = "productTypeDao.created")
  @Mapping(target = "updated", source = "productTypeDao.updated")
  ProductType productTypeDaoToProductType(ProductTypeDao productTypeDao);
  
  @Mapping(target = "id", source = "productType.id")
  @Mapping(target = "name", source = "productType.name")
  @Mapping(target = "description", source = "productType.description")
  @Mapping(target = "created", source = "productType.created")
  @Mapping(target = "updated", source = "productType.updated")
  ProductTypeDao productTypeToProductTypeDao(ProductType productType);
}
