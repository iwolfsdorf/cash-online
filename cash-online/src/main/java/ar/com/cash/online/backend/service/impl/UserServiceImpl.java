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
  public User createUser(User user) throws ServiceException {
    log.info("Se crea el usuario {}", user);
    return userRepository.save(user);
  }

  @Override
  public void deleteUser(Long id) throws ServiceException {
    log.info("Se elimina el usuario con identificador {}.", id);
    userRepository.deleteById(id);;
  }

  @Override
  public User getUser(Long id) throws ServiceException {
    log.info("Se obtiene el usuario con identificador {}.", id);
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      return optionalUser.get();
    }
    throw new ServiceException("No se encontro el usuario con identificador " + id);
  }

}
