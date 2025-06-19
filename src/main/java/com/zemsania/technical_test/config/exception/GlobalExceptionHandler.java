package com.zemsania.technical_test.config.exception;

import com.zemsania.technical_test.config.exception.dto.ApiError;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ApiError> handleExternalApiException(
            ExternalApiException ex,
            HttpServletRequest request) {

        ApiError apiError = new ApiError();
        apiError.setBackendMessage(ex.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setMessage(ex.getMessage());

        return ResponseEntity.status(ex.getStatusCode()).body(apiError);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiError> handleFeignException(
            FeignException ex,
            HttpServletRequest request) {

        ApiError apiError = new ApiError();
        apiError.setBackendMessage(ex.contentUTF8());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setMessage("Error al comunicarse con el servicio externo");

        return ResponseEntity.status(ex.status()).body(apiError);
    }
}
