package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.DoubleEliminationTournament;
import com.example.demo.models.Match;
import com.example.demo.models.RoundRobinTournament;
import com.example.demo.models.SingleEliminationTournament;
import com.example.demo.models.Tournament;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

@Autowired
private EntityManager em;


@Transactional
@GetMapping("/test")
public void testEntity(){
  Match m=new Match();
  em.persist(m);
  em.flush();
  em.clear();

  // Game g=new Game();
  // g.setId(1L);
  // em.persist(g);

  // Match retrievedMatch = em.find(Match.class, m.getId());
  // assertNotNull(retrievedMatch, "Le match devrait être retrouvé en base de données");
  // assertEquals(m.getId(), retrievedMatch.getId(), "Les IDs devraient être égaux");
}

@Transactional
@GetMapping("/testTournament")
public void testTournament(){
  Tournament singleEliminationTournament=new SingleEliminationTournament();
  singleEliminationTournament.setName("Tournoi smash bross 1 ");
  
  Tournament doubleEliminationTournament=new DoubleEliminationTournament();
  doubleEliminationTournament.setName("Tournoi test ");
  
  Tournament roundRobinTournament=new RoundRobinTournament();
  roundRobinTournament.setName("Tournoi roundbobin test");
  
  
  
  em.persist(singleEliminationTournament);
  em.persist(doubleEliminationTournament);
  em.persist(roundRobinTournament);
  em.flush();
  em.clear();

}

}
