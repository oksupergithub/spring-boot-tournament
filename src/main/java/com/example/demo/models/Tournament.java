package com.example.demo.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
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
  
  @ManyToMany
  @JoinTable(
      name = "tournament_team",
      joinColumns = @JoinColumn(name = "tournament_id"),
      inverseJoinColumns = @JoinColumn(name = "team_id")
  )
  private Set<Team> inscrits;

  @OneToMany(mappedBy = "tournament")
  private List<Match> matches;
}
