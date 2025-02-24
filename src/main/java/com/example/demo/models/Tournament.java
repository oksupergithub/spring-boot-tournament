package com.example.demo.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tournament_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Tournament implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private TournamentStatus status;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  @ManyToOne
  @JoinColumn(name = "game_mode_id")
  private GameMode gameMode;

  private Integer maxPlayers;
  
  @ManyToMany(mappedBy = "tournaments")
  private Set<Player> inscrits;

  @OneToMany
  @JoinColumn(name = "match_id")
  private List<Match> matches;
}
