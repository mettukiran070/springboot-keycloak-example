package com.example.keycloak.mapper;

import com.example.keycloak.dto.ProductDto;
import com.example.keycloak.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  Product mapDtoToModel(ProductDto productDto);

  ProductDto mapModelToDto(Product product);

}
