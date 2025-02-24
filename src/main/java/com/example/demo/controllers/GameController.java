package com.example.demo.controllers;

import com.example.demo.models.Game;
import com.example.demo.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // Créer un nouveau jeu
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game newGame = gameService.saveGame(game);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    // Récupérer tous les jeux
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    // Récupérer un jeu par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameService.getGameById(id)
                .map(game -> new ResponseEntity<>(game, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mettre à jour un jeu
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        return gameService.getGameById(id)
                .map(existingGame -> {
                    game.setId(id);
                    Game updatedGame = gameService.saveGame(game);
                    return new ResponseEntity<>(updatedGame, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Supprimer un jeu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        return gameService.getGameById(id)
                .map(game -> {
                    gameService.deleteGame(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
} 