package com.example.demo.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "players")
@DiscriminatorValue("PLAYER")
@Data
@EqualsAndHashCode(callSuper = true)
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



// @OneToMany
// @JoinColumn(name = "player_stats_id")
// private List<PlayerStats> playerStats;a
}