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

import java.util.Optional;

import ar.com.cash.online.backend.model.User;
import ar.com.cash.online.backend.repository.UserRepository;
import ar.com.cash.online.backend.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceTest {

  @TestConfiguration
  static class UserServiceImplTestContextConfiguration {
    @Bean
    public UserService userSerivce() {
      return new UserServiceImpl();
    }
  }

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  private User getUserNoCreated() {
    final User user = new User();
    user.setEmail("testUser@gmail.com");
    user.setFirst_name("prueba");
    user.setLast_name("test");
    return user;
  }

  private User getUserCreated() {
    final User user = getUserNoCreated();
    user.setId(2l);
    return user;
  }

  public Optional<User> getOptionalUser() {
    return Optional.of(getUserCreated());
  }

  @Test
  public void createUser() throws Exception {
    Mockito.when(userRepository.save(getUserNoCreated())).thenReturn(getUserCreated());
    final User user = userService.createUser(getUserNoCreated());
    assertEquals(user, getUserCreated());
  }

  @Test
  public void getUser() throws Exception {
    Mockito.when(userRepository.findById(2l)).thenReturn(getOptionalUser());
    final User user = userService.getUser(2l);
    assertEquals(user, getUserCreated());
  }

  @Test
  public void getUserNotExist() throws Exception {
    Mockito.when(userRepository.findById(2l)).thenReturn(Optional.empty());
    final User user = userService.getUser(2l);
    assertNull(user);
  }

}
