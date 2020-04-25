package hu.elte.prjgbackend.repositories;

import hu.elte.prjgbackend.models.Game;
import hu.elte.prjgbackend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {

}