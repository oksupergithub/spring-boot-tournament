package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.models.Player;
import com.example.demo.repositories.PlayerRepository;

@RepositoryRestController
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

}
