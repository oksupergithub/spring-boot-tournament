package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Player;
import com.example.demo.repositories.PlayerRepository;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/players/create")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        playerRepository.save(player);
        return ResponseEntity.ok(player);
    }

    @PostMapping("/players/createList")
    public ResponseEntity<List<Player>> createPlayers(@RequestBody List<Player> players) {
        List<Player> savedPlayers = new ArrayList<>();
        
        for (Player player : players) {
            player.setPassword(passwordEncoder.encode(player.getPassword()));
            savedPlayers.add(playerRepository.save(player));
        }
        
        return ResponseEntity.ok(savedPlayers);
    }

}
