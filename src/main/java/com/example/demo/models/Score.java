package com.example.demo.models;

import java.io.Serializable;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<Player, Integer> playerScores;
    
    private Player winner;

    private Boolean forfeit;

    @OneToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
