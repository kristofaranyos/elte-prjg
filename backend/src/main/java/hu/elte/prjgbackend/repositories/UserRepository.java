package hu.elte.prjgbackend.repositories;

import hu.elte.prjgbackend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
}