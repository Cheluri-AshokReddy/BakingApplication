package com.example.BankingManagementSystem.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>myMethodArugumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> mp=new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error->{
            String fieldName=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            mp.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(mp, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resouceNotFoundException(ResourceNotFoundException e){
        String message=e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> myAPIException(APIException e){
        String message=e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);

    }
}
