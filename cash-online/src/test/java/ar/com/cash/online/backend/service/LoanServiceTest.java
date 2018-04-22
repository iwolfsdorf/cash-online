package ar.com.cash.online.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.cash.online.backend.model.Loan;
import ar.com.cash.online.backend.model.Page;
import ar.com.cash.online.backend.model.Paging;
import ar.com.cash.online.backend.repository.LoanRepository;
import ar.com.cash.online.backend.service.impl.LoanServiceImpl;

@RunWith(SpringRunner.class)
public class LoanServiceTest {

  @TestConfiguration
  static class LoanServiceImplTestContextConfiguration {
    @Bean
    public LoanService loanSerivce() {
      return new LoanServiceImpl();
    }
  }

  @Autowired
  private LoanService loanService;

  @MockBean
  private LoanRepository loanRepository;

  public Page<Loan> getPageLoansFromSameUser() {
    final List<Loan> loans = new ArrayList<>();
    loans.add(new Loan(1l, new BigDecimal(5546.54), 2l));
    loans.add(new Loan(2l, new BigDecimal(2331), 2l));
    loans.add(new Loan(3l, new BigDecimal(800), 2l));
    final Paging paging = new Paging(100, 0, 2l);
    return new Page<Loan>(loans, paging);
  }

  public Page<Loan> getPageLoansFromDiferentUser() {
    final List<Loan> loans = new ArrayList<>();
    loans.add(new Loan(1l, new BigDecimal(5546.54), 2l));
    loans.add(new Loan(2l, new BigDecimal(23123123), 3l));
    loans.add(new Loan(3l, new BigDecimal(2331), 2l));
    loans.add(new Loan(4l, new BigDecimal(822100), 1l));
    loans.add(new Loan(5l, new BigDecimal(8897), 4l));
    final Paging paging = new Paging(100, 0, null);
    return new Page<Loan>(loans, paging);
  }

  public Page<Loan> getPageEmpty() {
    final Paging paging = new Paging(100, 0, null);
    return new Page<Loan>(null, paging);
  }

  @Test
  public void getPageWithUserId() throws Exception {
    Mockito.when(loanRepository.findAllByUserId(100, 0, 2l)).thenReturn(getPageLoansFromSameUser());
    final Page<Loan> pageLoans = loanService.getPage(100, 0, 2l);
    assertEquals(pageLoans, getPageLoansFromSameUser());
  }

  @Test
  public void getPageWithoutUserId() throws Exception {
    Mockito.when(loanRepository.findAllByUserId(100, 0, null)).thenReturn(getPageLoansFromDiferentUser());
    final Page<Loan> pageLoans = loanService.getPage(100, 0, null);
    assertEquals(pageLoans, getPageLoansFromDiferentUser());
  }

  @Test
  public void getPageWithoutValues() throws Exception {
    Mockito.when(loanRepository.findAllByUserId(100, 0, null)).thenReturn(getPageEmpty());
    final Page<Loan> pageLoans = loanService.getPage(100, 0, null);
    assertNull(pageLoans.getItems());
  }

}
