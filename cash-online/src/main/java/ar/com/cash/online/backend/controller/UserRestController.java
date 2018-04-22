package ar.com.cash.online.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import ar.com.cash.online.backend.exception.ServiceException;
import ar.com.cash.online.backend.exception.ws.InternalServerErrorException;
import ar.com.cash.online.backend.exception.ws.NotFoundException;
import ar.com.cash.online.backend.model.User;
import ar.com.cash.online.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

  private final Logger log = LoggerFactory.getLogger(UserRestController.class);

  @Autowired
  private UserService userService;

  @ResponseBody
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getUser(@PathVariable final Long id) {
    try {
      final User user = userService.getUser(id);
      if (user != null) {
        return new ResponseEntity<User>(user, HttpStatus.OK);
      } else {
        log.error("No se encontro el usuario con identificador {}.", id);
        throw new NotFoundException("No se encontro el usuario con identificador " + id);
      }
    } catch (final ServiceException e) {
      log.error("Se produjo un error desconocido.", e);
      throw new InternalServerErrorException("Se produjo un error interno.");
    }
  }

  @ResponseBody
  @DeleteMapping(value = "{id}", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> deleteUser(@PathVariable final Long id) {
    try {
      userService.deleteUser(id);
      return new ResponseEntity<String>("Usuario " + id + " eliminado correctamento.", HttpStatus.OK);
    } catch (final ServiceException e) {
      log.error("Se produjo un error desconocido.", e);
      throw new InternalServerErrorException("Se produjo un error interno.");
    }
  }

  @ResponseBody
  @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> createUser(@RequestBody final User user, final HttpServletRequest request) {
    try {
      userService.createUser(user);
      log.info("Se creo el usuario con identificador {}.", user.getId());
      return new ResponseEntity<String>("Se creo el usuario correctamento con id " + user.getId() + ".", HttpStatus.OK);
    } catch (final ServiceException e) {
      log.error("No se pudo crear el usuario.");
      throw new InternalServerErrorException("No se pudo crear el usuario.");
    }
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(final UserService userService) {
    this.userService = userService;
  }

}
