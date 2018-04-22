package ar.com.cash.online.backend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import ar.com.cash.online.backend.exception.ServiceException;
import ar.com.cash.online.backend.model.User;
import ar.com.cash.online.backend.repository.UserRepository;
import ar.com.cash.online.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Override
  public User createUser(final User user) throws ServiceException {
    log.info("Se crea el usuario {}", user);
    try {
      return userRepository.save(user);
    } catch (final Exception ex) {
      throw new ServiceException("Ocurrio un error al crear un usuario.", ex);
    }
  }

  @Override
  public void deleteUser(final Long id) throws ServiceException {
    log.info("Se elimina el usuario con identificador {}.", id);
    try {
      userRepository.deleteById(id);;
    } catch (final Exception ex) {
      throw new ServiceException("Ocurrio un error al eliminar el usuario con id: " + id, ex);
    }
  }

  @Override
  public User getUser(final Long id) throws ServiceException {
    log.info("Se obtiene el usuario con identificador {}.", id);
    try {
      final Optional<User> optionalUser = userRepository.findById(id);
      if (optionalUser.isPresent()) {
        return optionalUser.get();
      }
      return null;
    } catch (final Exception ex) {
      throw new ServiceException("Ocurrio un error al buscar el usuario con id: " + id, ex);
    }
  }

}
