package com.sartori.food_order.exception.handler;

import com.sartori.food_order.dto.error.ErrorResponse;
import com.sartori.food_order.dto.error.ValidationErrorDTO;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
    return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
    return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {
    List<ValidationErrorDTO> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> new ValidationErrorDTO(error.getField(), error.getDefaultMessage()))
            .toList();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleUnexpected(Exception ex) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }

  private ResponseEntity<Object> buildResponse(HttpStatus status, String message, Object details) {
    var error = new ErrorResponse(
      LocalDateTime.now(),
      status.value(),
      status.getReasonPhrase(),
      message,
      details
    );
    return ResponseEntity.status(status).body(error);
  }

  private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
    return buildResponse(status, message, null);
  }
}
