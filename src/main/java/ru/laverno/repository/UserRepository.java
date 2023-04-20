package ru.laverno.repository;

import org.springframework.data.repository.CrudRepository;
import ru.laverno.entitiy.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findUserByEmailIgnoreCase(String email);

    Optional<User> findUserByPhone(String phone);

    Iterable<User> findUsersByUsernameContainsIgnoreCase(String username);
}
