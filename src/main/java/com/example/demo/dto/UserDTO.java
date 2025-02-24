package com.example.demo.dto;

import com.example.demo.models.User;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;

    // Méthode pour convertir User vers UserDTO
    public static UserDTO fromUser(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }

    // Méthode pour mettre à jour un User existant avec les données du DTO
    public void updateUser(User user) {
        user.setUsername(this.username);
        user.setPassword(this.password);
    }
} 