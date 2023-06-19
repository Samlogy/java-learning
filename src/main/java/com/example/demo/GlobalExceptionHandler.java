package com.example.demo;

import com.example.demo.exception.APIError;
import com.example.demo.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<APIError> validationException(
            ValidationException ex,
            HttpServletRequest request){
        log.error("Ex : "+  ex.getLocalizedMessage()+  " - "+ request.getRequestURI() );

        APIError err = new APIError(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,new java.util.Date());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<APIError> genericException(
            Exception ex,
            HttpServletRequest request){
        log.error("Ex : "+  ex.getLocalizedMessage()+  " - "+ request.getRequestURI() );

        APIError err = new APIError(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR,new java.util.Date());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value= {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<APIError> handleNotFoundException(NotFoundException ex,
                                                            HttpServletRequest request) {
        log.error("Ex : "+  ex.getLocalizedMessage()+  " - "+ request.getRequestURI() );

        APIError err = new APIError(ex.getMessage(), HttpStatus.NOT_FOUND, new java.util.Date());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}