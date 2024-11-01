package com.satc.todolist.utils;

import java.util.List;

import org.springframework.validation.ObjectError;

public class ErrorUtils {

  public static String[] getMessages(List<ObjectError> errors) {
    int size = errors.size();
    String[] messages = new String[size];

    for (int i = 0; i < size; i++) {
      messages[i] = errors.get(i).getDefaultMessage();
    }

    return messages;
  }
}
