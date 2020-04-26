package hu.elte.prjgbackend.controllers;

import java.time.Instant;
import hu.elte.prjgbackend.models.Game;
import hu.elte.prjgbackend.repositories.GameRepository;
import hu.elte.prjgbackend.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/games")
public class GameController{

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Game>> getGames(){
        Iterable<Game> games = gameService.findAll();
        return ResponseEntity.ok(games);
    }

    @PutMapping("/")
    public ResponseEntity startGame(@RequestParam Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        try{
            Game game = gameService.findById(id);
            game.setIsRunning(true);
            game.setStartedDate(Instant.now().toEpochMilli());
            gameRepository.save(game);

            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}