package com.bootcamp.products.application.dto;

import com.bootcamp.products.domain.model.product.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductTypeDtoConverter {
  ProductTypeDtoConverter INSTANCE = Mappers.getMapper(ProductTypeDtoConverter.class);
  @Mapping(target = "id", source = "productType.id")
  @Mapping(target = "name", source = "productType.name")
  @Mapping(target = "description", source = "productType.description")
  @Mapping(target = "created", source = "productType.created")
  @Mapping(target = "updated", source = "productType.updated")
  ProductTypeDto productTypeToProductTypeDto(ProductType productType);
  
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", source = "productTypeDto.name")
  @Mapping(target = "description", source = "productTypeDto.description")
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "updated", ignore = true)
  ProductType productTypeDtoToProductType(ProductTypeDto productTypeDto);
}
