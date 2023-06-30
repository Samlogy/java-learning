package com.example.demo;

import com.example.demo.exception.APIError;
import com.example.demo.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Map<String, List<APIError>>> validationException(
            ValidationException ex,
            HttpServletRequest request){
        log.error("Ex : "+  ex.getLocalizedMessage()+  " - "+ request.getRequestURI() );

        APIError err = new APIError(ex.getMessage(), HttpStatus.BAD_REQUEST, new Date());
        List<APIError> errors = Collections.singletonList(err);
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value= {RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<Map<String, List<APIError>>> genericException(
            RuntimeException ex,
            HttpServletRequest request) {
        log.error("Ex : "+  ex.getLocalizedMessage()+  " - "+ request.getRequestURI() );

        APIError err = new APIError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new Date());
        List<APIError> errors = Collections.singletonList(err);
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value= {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ResponseEntity<Map<String, List<APIError>>> handleNotAllowedMethodException(
            NotFoundException ex,
            HttpServletRequest request) {
        log.error("Ex : "+  ex.getLocalizedMessage()+  " - "+ request.getRequestURI() );

        APIError err = new APIError(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED, new Date());
        List<APIError> errors = Collections.singletonList(err);
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value= {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Map<String, List<APIError>>> handleNotFoundException(
            NotFoundException ex,
            HttpServletRequest request) {
        log.error("Ex : "+  ex.getLocalizedMessage()+  " - "+ request.getRequestURI() );

        APIError err = new APIError(ex.getMessage(), HttpStatus.NOT_FOUND, new Date());
        List<APIError> errors = Collections.singletonList(err);
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    private Map<String, List<APIError>> getErrorsMap(List<APIError> errors) {
        Map<String, List<APIError>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}