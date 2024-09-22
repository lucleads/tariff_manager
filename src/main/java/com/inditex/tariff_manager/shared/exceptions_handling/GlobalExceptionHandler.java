package com.inditex.tariff_manager.shared.exceptions_handling;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.ErrorResponseDto;
import com.inditex.tariff_manager.tariff_management.infrastructure.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler({
        NotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handleException(HttpServletRequest request, Exception ex) {
        final ErrorResponseDto response = new ErrorResponseDto();
        response.setMessage(ex.getMessage());
        response.setRequestUrl(request.getRequestURL().toString());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings("java:S112")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralException(HttpServletRequest request, Exception ex) {
        final ErrorResponseDto response = new ErrorResponseDto();
        response.setMessage(ex.getMessage());
        response.setRequestUrl(request.getRequestURL().toString());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}