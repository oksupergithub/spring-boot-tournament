package com.example.demo.services;

import com.example.demo.models.Tournament;
import com.example.demo.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    // Sauvegarder ou mettre à jour un tournoi
    public Tournament saveTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    // Récupérer tous les tournois
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    // Récupérer un tournoi par son ID
    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    // Supprimer un tournoi
    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }
} 