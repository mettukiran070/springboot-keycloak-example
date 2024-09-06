package com.example.keycloak.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductException extends RuntimeException {

  public ProductException(String msg) {
    super(msg);
  }

  public ProductException(Throwable throwable) {
    super(throwable);
  }

  public ProductException(String msg, Throwable throwable) {
    super(msg, throwable);
  }

}
