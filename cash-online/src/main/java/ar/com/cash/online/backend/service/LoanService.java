package ar.com.cash.online.backend.service;

import ar.com.cash.online.backend.exception.ServiceException;
import ar.com.cash.online.backend.model.Loan;
import ar.com.cash.online.backend.model.Page;

public interface LoanService {

  /**
   * Obtiene una pagina de los prestamos de los usuarios.
   *
   * @see Page
   * @see Loan
   *
   * @author Ivan Wolfsdorf
   * 
   * @return Page<Loan> pagina de prestamos.
   * @throws ServiceException En caso de que ocurra un error al obtener la pagina de prestamos.
   */
  public Page<Loan> getPage(Integer limit, Integer offset, Long user_id) throws ServiceException;

}
