package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.SingleEliminationTournament;
import com.example.demo.repositories.SingleEliminationTournamentRepository;

@RestController
public class SingleEliminationTournamentController {
  
  @Autowired
  private SingleEliminationTournamentRepository singleEliminationTournamentRepository;

  @PostMapping("/singleElimTourn/create")
  public ResponseEntity<SingleEliminationTournament> createSingleEliminationTournament(@RequestBody SingleEliminationTournament singleEliminationTournament){
    singleEliminationTournamentRepository.save(singleEliminationTournament);
    return ResponseEntity.ok(singleEliminationTournament);
  }

}
