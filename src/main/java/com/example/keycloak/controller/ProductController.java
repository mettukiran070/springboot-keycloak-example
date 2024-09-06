package com.example.keycloak.controller;

import com.example.keycloak.dto.ProductDto;
import com.example.keycloak.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/products")
  public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) {
    ProductDto response = this.productService.save(productDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/products")
  public ResponseEntity<List<ProductDto>> findAll() {
    List<ProductDto> products = this.productService.findAll();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<ProductDto> findAll(@PathVariable Long id) {
    ProductDto product = this.productService.findById(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

}
