package ar.com.cash.online.backend.repository;

import org.springframework.data.repository.Repository;

import javax.persistence.criteria.Predicate;

import ar.com.cash.online.backend.exception.ServiceException;
import ar.com.cash.online.backend.model.Loan;
import ar.com.cash.online.backend.model.Page;

public interface LoanRepository extends Repository<Loan, Long> {

  /**
   * Obtiene una pagina de los prestamos de los usuarios.
   *
   * @see Loan
   *
   * @author Ivan Wolfsdorf
   * 
   * @param limit Integer maxima cantidad de registros a buscar.
   * @param offset Integer cantidad de registros omitidos.
   * @param user_id Long identificador del usuario.
   * 
   * @return Page<Loan> pagina de prestamos.
   * @throws ServiceException En caso de que ocurra un error al obtener la pagina de prestamos.
   */
  Page<Loan> findAllByUserId(Integer limit, Integer offset, Long user_id);

  /**
   * Obtiene la cantidad de prestamos dado un predicado. Si este es nulo entonces realiza un conteo
   * de todos los prestamos.
   * 
   * @see Predicate
   *
   * @author Ivan Wolfsdorf
   * 
   * @param predicate Predicate usado en el where.
   * 
   * @return Page<Loan> pagina de prestamos.
   * @throws ServiceException En caso de que ocurra un error al obtener la pagina de prestamos.
   */
  Long count(Predicate predicate);

}
