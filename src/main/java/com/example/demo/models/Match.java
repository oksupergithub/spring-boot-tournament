package com.example.demo.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
 
  private LocalDateTime scheduledTime;
  
  @Enumerated(EnumType.STRING)
 
  private MatchStatus status;

  @ManyToOne
  @JoinColumn(name = "tournament_id")
  private Tournament tournament;

  @OneToOne
  @JoinColumn(name = "match_id")
  private Score score;

  @ManyToMany
  @JoinTable(name = "match_players",
    joinColumns = @JoinColumn(name = "match_id"),
    inverseJoinColumns = @JoinColumn(name = "player_id")
  )
  private List<Player> players;
}
