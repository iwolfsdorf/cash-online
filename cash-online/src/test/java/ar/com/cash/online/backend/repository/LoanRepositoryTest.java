package ar.com.cash.online.backend.repository;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.cash.online.backend.model.Loan;
import ar.com.cash.online.backend.model.Page;
import ar.com.cash.online.backend.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private LoanRepository loanRepository;

  @Test
  public void getPage() throws Exception {
    User user = new User();
    user.setEmail("prueba@gmail.com");
    user.setFirst_name("prueba");
    user.setLast_name("test");
    user = entityManager.persist(user);
    final List<Loan> loans = new ArrayList<>();
    loans.add(new Loan(new BigDecimal(500.00), user.getId()));
    loans.add(new Loan(new BigDecimal(1500.00), user.getId()));
    loans.add(new Loan(new BigDecimal(2500.00), user.getId()));
    user.setLoans(loans);
    entityManager.persist(user);
    entityManager.flush();

    final Page<Loan> page = loanRepository.findAllByUserId(100, 0, user.getId());

    assertArrayEquals(page.getItems().toArray(), loans.toArray());
  }

  @Test
  public void getPageWithoutUserId() throws Exception {
    User user = new User();
    user.setEmail("prueba@gmail.com");
    user.setFirst_name("prueba");
    user.setLast_name("test");
    user = entityManager.persist(user);
    final List<Loan> loans = new ArrayList<>();
    loans.add(new Loan(new BigDecimal(500.00), user.getId()));
    loans.add(new Loan(new BigDecimal(1500.00), user.getId()));
    loans.add(new Loan(new BigDecimal(2500.00), user.getId()));
    user.setLoans(loans);
    entityManager.persist(user);
    User user2 = new User();
    user2.setEmail("prueba@gmail.com");
    user2.setFirst_name("prueba");
    user2.setLast_name("test");
    user2 = entityManager.persist(user2);
    final List<Loan> loans2 = new ArrayList<>();
    loans2.add(new Loan(new BigDecimal(33200.00), user2.getId()));
    loans2.add(new Loan(new BigDecimal(212300.00), user2.getId()));
    user2.setLoans(loans2);
    entityManager.persist(user2);
    entityManager.flush();

    final Page<Loan> page = loanRepository.findAllByUserId(100, 0, null);

    final List<Loan> loansTotales = new ArrayList<>();
    loansTotales.addAll(loans);
    loansTotales.addAll(loans2);

    assertArrayEquals(page.getItems().toArray(), loansTotales.toArray());
  }

}
