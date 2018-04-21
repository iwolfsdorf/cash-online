package ar.com.cash.online.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.cash.online.backend.exception.ServiceException;
import ar.com.cash.online.backend.exception.ws.InternalServerErrorException;
import ar.com.cash.online.backend.model.Loan;
import ar.com.cash.online.backend.model.Page;
import ar.com.cash.online.backend.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanRestController {

  private final Logger log = LoggerFactory.getLogger(LoanRestController.class);

  @Autowired
  private LoanService loanService;

  @ResponseBody
  @GetMapping(produces = "application/json")
  public Page<Loan> getPage(@RequestParam(name = "limit", required = true) final Integer limit, @RequestParam(name = "offset", required = true) final Integer offset,
      @RequestParam(name = "user_id", required = false) final Long user_id) {
    try {
      return loanService.getPage(limit, offset, user_id);
    } catch (final ServiceException e) {
      log.error("Se produjo un error al buscar la pagina.", e);
      throw new InternalServerErrorException("Se produjo un error inesperado.");
    }
  }

}
