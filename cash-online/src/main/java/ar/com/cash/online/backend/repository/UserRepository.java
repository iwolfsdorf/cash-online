package ar.com.cash.online.backend.repository;

import org.springframework.data.repository.CrudRepository;

import ar.com.cash.online.backend.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
