package ar.com.cash.online.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.cash.online.backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
