package com.example.keycloak.service;

import com.example.keycloak.dto.ProductDto;
import com.example.keycloak.exception.ProductNotFoundException;
import com.example.keycloak.mapper.ProductMapper;
import com.example.keycloak.model.Product;
import com.example.keycloak.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public ProductDto save(ProductDto productDto) {
    Product product = productMapper.mapDtoToModel(productDto);
    Product savedProduct = this.productRepository.save(product);
    return productMapper.mapModelToDto(savedProduct);
  }

  public List<ProductDto> findAll() {
    List<Product> products = this.productRepository.findAll();
    return products.stream().map(productMapper::mapModelToDto).collect(Collectors.toList());
  }

  public ProductDto findById(Long id) {
    Optional<Product> optionalProduct = this.productRepository.findById(id);
    return optionalProduct
        .map(productMapper::mapModelToDto)
        .orElseThrow(() -> new ProductNotFoundException("Unable to find the product "+ id));
  }

}
