package com.example.demo.services;

import com.example.demo.models.Game;
import com.example.demo.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    // Sauvegarder ou mettre à jour un jeu
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    // Récupérer tous les jeux
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // Récupérer un jeu par son ID
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    // Supprimer un jeu
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
} 