package hu.elte.prjgbackend.repositories;

import hu.elte.prjgbackend.models.User;
import hu.elte.prjgbackend.models.UserSession;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserSessionRepository extends CrudRepository<UserSession, Long> {
    Optional<UserSession> findByUser(User user);
    Optional<UserSession> findByToken(UUID token);
}