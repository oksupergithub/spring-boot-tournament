package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.example.demo.models.Game;
import com.example.demo.models.GameMode;

import jakarta.validation.ConstraintViolation;


class GameModeTest {
    private LocalValidatorFactoryBean validator;
    private Game game;
    private GameMode gameMode;

    @BeforeEach
    void setUp(){
      validator = new LocalValidatorFactoryBean();
      validator.afterPropertiesSet();

      game = new Game();
      game.setId(Long.valueOf(1L));
      game.setName("Test Game");

      // gameMode = new GameMode();
      // gameMode.setId(Long.valueOf(1L));
      // gameMode.setName("standard");
      // gameMode.setMinPlayers(2);
      // gameMode.setMaxPlayers(4);

      // gameMode = new GameMode(1L, "standard", "un mode de jeu", game, 2, 4, 100);
    }

    @Test
    @DisplayName("Test de la validation du nombre de joueurs minimum")
    void testMinPlayers(){
      gameMode.setMinPlayers(-1);
      Set<ConstraintViolation<GameMode>> result = validator.validate(gameMode);
      assertFalse(result.isEmpty());
      assertEquals(1, result.size());
      // Lorsque le minplayers est négatif
      // Le message d'erreur envoyé doit être le bon 
      assertEquals("Le nombre de joueurs minimum doit être d'au moins 1", result.iterator().next().getMessage());
        

    }
} 