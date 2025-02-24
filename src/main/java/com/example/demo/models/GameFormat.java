  package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameFormat {
    SOLO_BATTLE_ROYALE,
    MOBA_5V5,
    CARD_1V1,
    RACING_SOLO,
    STRATEGY
} 