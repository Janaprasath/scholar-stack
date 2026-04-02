package com.janaprasath.scholarstack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(io.jsonwebtoken.security.SignatureException.class)
    public ResponseEntity<Map<String, String>> handleInvalidToken() {
        Map<String, String> error = new HashMap<>();
        error.put("status", "401");
        error.put("error", "Unauthorized");
        error.put("message", "Invalid or tampered security token. Please login again.");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralError(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("status", "500");
        error.put("error", "Internal Server Error");
        error.put("message", e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}