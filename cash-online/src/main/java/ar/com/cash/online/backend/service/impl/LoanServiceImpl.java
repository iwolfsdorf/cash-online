package ar.com.cash.online.backend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.cash.online.backend.exception.ServiceException;
import ar.com.cash.online.backend.model.Loan;
import ar.com.cash.online.backend.model.Page;
import ar.com.cash.online.backend.repository.LoanRepository;
import ar.com.cash.online.backend.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

  private final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

  @Autowired
  private LoanRepository loanRepository;

  @Override
  public Page<Loan> getPage(final Integer limit, final Integer offset, final Long user_id) throws ServiceException {
    log.info("Se busca pagina de Loans. limit: {}, offset: {}, user_id: {}.", limit, offset, user_id);
    try {
      return loanRepository.findAllByUserId(limit, offset, user_id);
    } catch (final Exception ex) {
      throw new ServiceException("Ocurrio un error al obtener una pagina.", ex);
    }
  }

}
