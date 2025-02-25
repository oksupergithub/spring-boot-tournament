package com.example.demo.models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "players")
@DiscriminatorValue("PLAYER")
@Data
@ToString(exclude = {"teams", "matches", "tournaments"})
@EqualsAndHashCode(exclude = {"teams", "matches", "tournaments"}, callSuper = true)
@JsonIgnoreProperties(value = {"teams"})
public class Player extends User {
    
    @Column(nullable = false)
    private String nickname;
    
    @Column(name = "elo_rating")
    private Integer eloRating;
    
    // // Configuration correcte de la Map avec JPA
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "player_id")
    // @MapKeyJoinColumn(name = "game_id")  // Spécifie la colonne pour la clé de la Map
    // @MapKey(name = "id")                 // Utilise l'ID du Game comme clé
    // private Map<Game, PlayerStats> statistics = new HashMap<>();
    
    @ManyToMany
    @JoinTable(
        name = "player_matches",
        joinColumns = @JoinColumn(name = "player_id"),
        inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    private List<Match> matches;

    @ManyToMany
    private Set<Tournament> tournaments;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
        name = "player_teams",
        joinColumns = @JoinColumn(name = "player_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams;


// @OneToMany
// @JoinColumn(name = "player_stats_id")
// private List<PlayerStats> playerStats;a
}