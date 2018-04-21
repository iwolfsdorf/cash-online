package ar.com.cash.online.backend.utils;

import java.time.LocalDateTime;
import java.util.Optional;

import ar.com.cash.online.backend.exception.model.RestError;
import ar.com.cash.online.backend.exception.ws.RestControllerException;

public abstract class RestErrorBuilder {

  /**
   * Genera un RestError para devolver en caso de una excepcion en el controller.
   *
   * @see RestError
   * @see RestControllerException
   *
   * @author Ivan Wolfsdorf
   * 
   * @param ex RestControllerException excepcionRest a convertir en un RestError.
   * 
   * @return RestError generado con la informaicion de un RestControllerException.
   */
  public static RestError from(final RestControllerException ex) {
    final RestError errorMessage = new RestError();
    errorMessage.setTimestamp(LocalDateTime.now());
    errorMessage.setStatus(ex.getStatus().value());
    errorMessage.setError(ex.getStatus().getReasonPhrase());
    errorMessage.setMessage(Optional.of(ex.getMessage()).orElse(ex.getClass().getSimpleName()));
    return errorMessage;
  }

}
