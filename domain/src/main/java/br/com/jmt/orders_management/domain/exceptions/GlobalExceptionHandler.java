package br.com.jmt.orders_management.domain.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorResponseWrapper> handleException(Exception exception, WebRequest request) {

        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();

        return ResponseEntity
                .badRequest()
                .body(new HttpErrorResponseWrapper(BAD_REQUEST, exception.getMessage(), requestURI));
    }
}