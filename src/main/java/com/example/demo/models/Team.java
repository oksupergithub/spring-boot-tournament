package com.example.demo.models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"teamPlayers", "matches", "tournaments"})
@EqualsAndHashCode(exclude = {"teamPlayers", "matches", "tournaments"})
@JsonIgnoreProperties(value = {"teamPlayers"})
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @JsonManagedReference
  @ManyToMany(mappedBy = "teams")
  private Set<Player> teamPlayers;

  @OneToMany(mappedBy = "team")
  private List<Match> matches;
  
  @ManyToMany(mappedBy = "inscrits")
  private Set<Tournament> tournaments;
  
}
