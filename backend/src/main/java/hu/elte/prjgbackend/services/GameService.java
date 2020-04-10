package hu.elte.prjgbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.elte.prjgbackend.models.Game;

import hu.elte.prjgbackend.repositories.GameRepository;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Iterable<Game> findAll(){
       return gameRepository.findAll();
    }

    public Game findById(Long id) { return gameRepository.findById(id).get();}
}
