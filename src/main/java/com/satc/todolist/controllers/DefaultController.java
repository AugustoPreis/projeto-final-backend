package com.satc.todolist.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.satc.todolist.dtos.RequestErrorDTO;
import com.satc.todolist.utils.ErrorUtils;
import com.satc.todolist.utils.RequestError;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class DefaultController {

  @ExceptionHandler(RequestError.class)
  @ResponseBody
  public RequestErrorDTO erro(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, RequestError requestError) {
    httpServletResponse.setStatus(requestError.getStatus().value());

    return new RequestErrorDTO(requestError.getStatus(), new String[] { requestError.getMessage() });
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public RequestErrorDTO erroValidacao(HttpServletRequest httpServletRequest,
      MethodArgumentNotValidException exception) {
    String[] messages = ErrorUtils.getMessages(exception.getBindingResult().getAllErrors());

    return new RequestErrorDTO(HttpStatus.BAD_REQUEST, messages);
  }
}
