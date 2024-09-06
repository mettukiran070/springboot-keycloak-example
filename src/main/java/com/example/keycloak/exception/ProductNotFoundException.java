package com.example.keycloak.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String msg) {
    super(msg);
  }

  public ProductNotFoundException(Throwable throwable) {
    super(throwable);
  }

  public ProductNotFoundException(String msg, Throwable throwable) {
    super(msg, throwable);
  }

}
