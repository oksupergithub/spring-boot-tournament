package com.example.demo.models;

import jakarta.persistence.Entity;
import java.io.Serializable;

@Entity
public class DoubleEliminationTournament extends Tournament implements Serializable{
  
  private Boolean hasConsolationFinal;
}
