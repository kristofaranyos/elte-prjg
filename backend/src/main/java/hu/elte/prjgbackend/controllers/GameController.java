package hu.elte.prjgbackend.controllers;

import java.util.ArrayList;
import java.util.List;

import hu.elte.prjgbackend.models.Game;
import hu.elte.prjgbackend.repositories.GameRepository;
import hu.elte.prjgbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hu.elte.prjgbackend.models.Location;

@RestController
@RequestMapping("/games")
public class GameController{

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Game>> getGames(){
        Iterable<Game> games = gameRepository.findAll();
        return ResponseEntity.ok(games);
    }
}