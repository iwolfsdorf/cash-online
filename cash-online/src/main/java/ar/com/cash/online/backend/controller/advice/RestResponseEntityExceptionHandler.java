package ar.com.cash.online.backend.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.cash.online.backend.exception.model.RestError;
import ar.com.cash.online.backend.exception.ws.RestControllerException;
import ar.com.cash.online.backend.utils.RestErrorBuilder;

@ControllerAdvice(annotations = RestController.class)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = RestControllerException.class)
  protected ResponseEntity<RestError> handleNotFound(final RuntimeException ex, final WebRequest request) {
    final RestControllerException exception = (RestControllerException) ex;
    final RestError body = RestErrorBuilder.from(exception);
    return new ResponseEntity<>(body, exception.getStatus());
  }

}
