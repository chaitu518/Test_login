package com.example.test_login.exceptions;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst().map(fe -> fe.getField() + " " + fe.getDefaultMessage()).orElse("Validation error");
        return ResponseEntity.badRequest().body(Map.of("status", 400, "message", msg));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        String msg = ex.getMessage() == null ? "Error" : ex.getMessage();
        int status = "Invalid credentials".equalsIgnoreCase(msg) ? 401 : 400;
        return ResponseEntity.status(status).body(Map.of("status", status, "message", msg));
    }
}
