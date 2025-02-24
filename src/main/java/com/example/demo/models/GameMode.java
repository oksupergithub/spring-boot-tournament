package com.example.demo.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GameMode implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Le nom ne peut pas être vide")
  @Size(min = 1 , message = "Le nom doit contenir au moins 1 caractère")
  private String name;

  private String description;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  @NotNull(message = "Le nombre de joueurs minimum ne peut pas être nul")
  @Min(value = 1, message = "Le nombre de joueurs minimum doit être d'au moins 1")
  private Integer minPlayers;

  @NotNull(message = "Le nombre de joueurs minimum ne peut pas être nul")
  @Min(value = 2, message = "Le nombre de joueurs maximum doit être d'au moins 2")
  private Integer maxPlayers;
  private Integer scoreToWin;

  @OneToMany
  @JoinColumn(name = "game_mode_id")
  private List<Tournament> tournaments;
}
