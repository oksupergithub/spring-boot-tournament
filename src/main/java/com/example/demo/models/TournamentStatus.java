package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TournamentStatus {
  DRAFT,
  REGISTRATION_OPEN,
  REGISTRATION_CLOSED,
  IN_PROGRESS,
  COMPLETED,
  CANCELLED;

  public boolean isFinished(){
    return this == COMPLETED || this == CANCELLED;
  }

  public boolean isActive(){
    return this == IN_PROGRESS || this == REGISTRATION_OPEN;
  }

  public boolean isConfigurable(){
    return this == DRAFT || this == REGISTRATION_CLOSED;
  }
  public boolean canTransitionTo(TournamentStatus status){
    switch(this){
      case DRAFT:
        return status == REGISTRATION_OPEN;

      case REGISTRATION_OPEN:
        return status == IN_PROGRESS || status == REGISTRATION_CLOSED;

      case IN_PROGRESS:
        return status == COMPLETED || status == CANCELLED;

      case REGISTRATION_CLOSED:
        return status == IN_PROGRESS;
        
      case CANCELLED:
        return status == COMPLETED;
      default:
        return false;
    }
  }
}
