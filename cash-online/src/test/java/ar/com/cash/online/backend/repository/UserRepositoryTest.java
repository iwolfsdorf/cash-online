package ar.com.cash.online.backend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import ar.com.cash.online.backend.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void createUser() throws Exception {
    final User user = new User("prueba@gmail.com", "prueba", "test");
    final User userCreate = userRepository.save(user);
    assertEquals(userCreate, user);
  }

  @Test
  public void findUser() throws Exception {
    final User user = new User("prueba@gmail.com", "prueba", "test");
    entityManager.persist(user);
    entityManager.flush();

    final Optional<User> userInDb = userRepository.findById(1l);
    if (userInDb.isPresent()) {
      assertEquals(userInDb.get(), user);
    } else {
      fail();
    }
  }

  @Test
  public void deleteUser() throws Exception {
    User user = new User("prueba@gmail.com", "prueba", "test");
    user = entityManager.persist(user);
    entityManager.flush();
    userRepository.delete(user);
    final Optional<User> optionalUser = userRepository.findById(user.getId());
    if (optionalUser.isPresent()) {
      fail();
    }
  }

}
