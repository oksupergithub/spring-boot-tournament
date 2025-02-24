package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.GameMode;

public interface GameModeRepository extends JpaRepository<GameMode, Long> {

}
