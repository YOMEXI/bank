package com.example.bank.Exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)

    ResponseEntity handleBindingErrors (MethodArgumentNotValidException exception){

        List errorList = exception.getFieldErrors().stream().map(fieldError -> {
            Map<String,String> errorMap = new HashMap<>();
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());

            return errorMap;
        }).collect(Collectors.toList());

        return  ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler(CustomerApiException.class)

    ResponseEntity handleCustomeApErrors (CustomerApiException exception){
        Map errorMap =new HashMap<>();
            errorMap.put("Message",exception.getMessage());

        return  ResponseEntity.badRequest().body(errorMap);
    }
}
