package ar.com.cash.online.backend.service;

import ar.com.cash.online.backend.exception.ServiceException;
import ar.com.cash.online.backend.model.User;

public interface UserService {

  /**
   * Crea un usuario en la Base de datos.
   *
   * @see User
   *
   * @author Ivan Wolfsdorf
   * 
   * @param user User usuario a crear.
   * 
   * @return User usuario creado.
   * @throws ServiceException En caso de que ocurra un error al crear un usuario.
   */
  public User createUser(User user) throws ServiceException;

  /**
   * Elimina un usuario de la base de datos dado su identificador.
   *
   * @author Ivan Wolfsdorf
   * 
   * @param id Long identificador del usuario.
   * 
   * @throws ServiceException En caso de que ocurra un error al eliminar un usuario.
   */
  public void deleteUser(Long id) throws ServiceException;

  /**
   * Obtiene un usuario dado su identificador.
   *
   * @see User
   *
   * @author Ivan Wolfsdorf
   * 
   * @param id Long identificador del usuario.
   * 
   * @return User usuario obtenido.
   * @throws ServiceException En caso de que ocurra un error al obtener un usuario.
   */
  public User getUser(Long id) throws ServiceException;

}
