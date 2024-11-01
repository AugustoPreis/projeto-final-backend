package com.satc.todolist.dtos;

import org.springframework.http.HttpStatus;

public record RequestErrorDTO(HttpStatus status, String[] errors) {
}