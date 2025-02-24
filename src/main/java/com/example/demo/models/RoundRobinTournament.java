package com.example.demo.models;

import java.io.Serializable;

import jakarta.persistence.Entity;

@Entity
public class RoundRobinTournament extends Tournament implements Serializable{

  private Integer numberOfRounds;
  private Boolean homeAndAway;
}
