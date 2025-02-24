package com.example.demo.controllers;

import com.example.demo.models.Tournament;
import com.example.demo.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    // Créer un nouveau tournoi
    @PostMapping
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        Tournament newTournament = tournamentService.saveTournament(tournament);
        return new ResponseEntity<>(newTournament, HttpStatus.CREATED);
    }

    // Récupérer tous les tournois
    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }

    // Récupérer un tournoi par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(tournament -> new ResponseEntity<>(tournament, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mettre à jour un tournoi
    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return tournamentService.getTournamentById(id)
                .map(existingTournament -> {
                    tournament.setId(id);
                    Tournament updatedTournament = tournamentService.saveTournament(tournament);
                    return new ResponseEntity<>(updatedTournament, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Supprimer un tournoi
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(tournament -> {
                    tournamentService.deleteTournament(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
} 