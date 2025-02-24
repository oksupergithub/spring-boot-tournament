package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MatchStatus {
  PENDING,
  IN_PROGRESS,
  COMPLETED,
  CANCELLED,
  POSTPONED,
  DISPUTED;

  // Méthode utilitaire requireAttention(), isFinal(), isActive()
  public boolean requireAttention(){
    return this == PENDING || this == POSTPONED;
  }

  public boolean isFinal(){
    return this == COMPLETED || this == CANCELLED || this == DISPUTED;
  }

  public boolean isActive(){
    return this == IN_PROGRESS;
  }
  
  
  
}