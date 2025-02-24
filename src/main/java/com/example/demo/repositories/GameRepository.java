package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
    // Les méthodes de base CRUD sont déjà fournies par JpaRepository
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
} 