package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}

