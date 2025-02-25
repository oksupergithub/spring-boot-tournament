package com.example.demo.controllers;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Player;
import com.example.demo.models.Team;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.repositories.TeamRepository;

@RestController
public class TeamController {

  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  TeamRepository teamRepository;

  @PutMapping("/teams/{teamId}/add-players")
  public ResponseEntity<Team> addPlayersToTeam(@PathVariable Long teamId, @RequestBody List<Long> playerIds) {
      Team team = teamRepository.findById(teamId)
              .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));
      
      // Initialiser la collection si elle est null
      if (team.getTeamPlayers() == null) {
          team.setTeamPlayers(new HashSet<>());
      }
      
      for (Long playerId : playerIds) {
          Player existingPlayer = playerRepository.findById(playerId)
                  .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + playerId));
          
          if (existingPlayer.getTeams() == null) {
              existingPlayer.setTeams(new HashSet<>());
          }
          
          // Ajouter la relation bidirectionnelle
          existingPlayer.getTeams().add(team);
          team.getTeamPlayers().add(existingPlayer);
      }
      
      Team updatedTeam = teamRepository.save(team);
      
      return ResponseEntity.ok(updatedTeam);
  }}