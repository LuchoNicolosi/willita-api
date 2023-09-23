package com.pastry.pastryapi.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ResponseDto<T> {
    private String message;
    private HttpStatus status;
    private LocalDateTime createdAt;
    private T data;

    public ResponseDto(String message, HttpStatus status, LocalDateTime createdAt, T data) {
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.data = data;
    }
}
