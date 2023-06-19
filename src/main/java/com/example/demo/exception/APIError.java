package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class APIError {
    private String message;
    private HttpStatus status;
    private Date time;
}