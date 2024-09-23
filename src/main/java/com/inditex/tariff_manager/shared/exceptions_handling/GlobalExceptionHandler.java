package com.inditex.tariff_manager.shared.exceptions_handling;

import com.inditex.tariff_manager.entrypoints.tariff_management.dto.ErrorResponseDto;
import com.inditex.tariff_manager.tariff_management.infrastructure.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    public static final String TECHNICAL_ERROR_MESSAGE = "TECHNICAL ERROR";
    public static final String INVALID_REQUEST_MESSAGE = "INVALID REQUEST";

    @ExceptionHandler({
        NotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(HttpServletRequest request, Exception ex) {
        final ErrorResponseDto response = new ErrorResponseDto();
        response.setMessage(ex.getMessage());
        response.setRequestUrl(request.getRequestURL().toString());
        log.error(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
        MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErrorResponseDto> handleBadRequestGenericException(HttpServletRequest request, Exception ex) {
        final ErrorResponseDto response = new ErrorResponseDto();
        response.setMessage(INVALID_REQUEST_MESSAGE);
        response.setRequestUrl(request.getRequestURL().toString());
        log.error(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
        MissingServletRequestParameterException.class
    })
    public ResponseEntity<ErrorResponseDto> handleBadRequestWithDetailsException(HttpServletRequest request,
        Exception ex) {
        final ErrorResponseDto response = new ErrorResponseDto();
        response.setMessage(ex.getMessage());
        response.setRequestUrl(request.getRequestURL().toString());
        log.error(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("java:S112")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralException(HttpServletRequest request, Exception ex) {
        final ErrorResponseDto response = new ErrorResponseDto();
        response.setMessage(TECHNICAL_ERROR_MESSAGE);
        response.setRequestUrl(request.getRequestURL().toString());
        log.error(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}