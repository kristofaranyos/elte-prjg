package hu.elte.prjgbackend.repositories;

import hu.elte.prjgbackend.models.Game;

import org.springframework.data.repository.CrudRepository;


public interface GameRepository extends CrudRepository<Game, Long> {

}