package com.example.demo.models;

import java.io.Serializable;

import jakarta.persistence.Entity;

@Entity
public class SingleEliminationTournament extends Tournament implements Serializable{
   private Boolean hasThirdPlaceMatch;
}
