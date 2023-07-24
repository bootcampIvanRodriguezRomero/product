package com.bootcamp.products.application.dto;

import com.bootcamp.products.domain.model.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductDtoConverter {
  ProductDtoConverter INSTANCE = Mappers.getMapper(ProductDtoConverter.class);
  @Mapping(target = "id", source = "product.id")
  @Mapping(target = "type", source = "product.type")
  @Mapping(target = "isActive", source = "product.isActive")
  @Mapping(target = "balance", source = "product.balance")
  @Mapping(target = "interestRate", source = "product.interestRate")
  @Mapping(target = "data", source = "product.data")
  @Mapping(target = "created", source = "product.created")
  @Mapping(target = "updated", source = "product.updated")
  @Mapping(target = "clients", source = "product.clients")
  ProductDto productToProductDto(Product product);
  
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "type", source = "productDto.type")
  @Mapping(target = "isActive", source = "productDto.isActive")
  @Mapping(target = "balance", source = "productDto.balance")
  @Mapping(target = "interestRate", source = "productDto.interestRate")
  @Mapping(target = "data", source = "productDto.data")
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "updated", ignore = true)
  @Mapping(target = "clients", ignore = true)
  Product productDtoToProduct(ProductDto productDto);
  
}
