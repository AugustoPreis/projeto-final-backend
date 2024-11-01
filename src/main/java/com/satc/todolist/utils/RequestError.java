package com.satc.todolist.utils;

import org.springframework.http.HttpStatus;

public class RequestError extends Error {
  HttpStatus status;

  public RequestError(HttpStatus status, String message) {
    super(message);

    this.setStatus(status);
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
