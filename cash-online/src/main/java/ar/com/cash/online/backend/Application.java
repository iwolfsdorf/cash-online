package ar.com.cash.online.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ar.com.cash"})
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
