package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;

import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtAuthFilter;

/**
 * Configuration de la sécurité de l'application
 * Cette classe gère l'authentification et les autorisations
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Repository pour accéder aux données utilisateur
    @Autowired
    private UserRepository userRepository;

    // Clé secrète pour signer/vérifier les JWT, récupérée depuis application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * Configure la chaîne de filtres de sécurité
     * @param http Configuration de la sécurité HTTP
     * @return La chaîne de filtres configurée
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Désactive CSRF car nous utilisons des JWT
            .csrf(csrf -> csrf.disable())
            // Configure les règles d'autorisation des requêtes
            .authorizeHttpRequests(auth -> auth
                // Routes publiques : login et création de compte
                .requestMatchers("/api/login", "/players/create","/players/createList").permitAll()
                // Toutes les autres routes nécessitent une authentification
                .anyRequest().authenticated()
            )
            // Ajoute notre filtre JWT avant le filtre d'authentification standard
            .addFilterBefore(new JwtAuthFilter(jwtSecret), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    /**
     * Configure l'encodeur de mot de passe
     * @return BCryptPasswordEncoder pour le hachage des mots de passe
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure le service de détails utilisateur
     * Utilisé par Spring Security pour charger les utilisateurs
     * @return Service qui charge les utilisateurs depuis la base de données
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
            .map(player -> User.builder()
                .username(player.getUsername())
                .password(player.getPassword())
                .roles("USER")  // Attribue le rôle USER à tous les joueurs
                .build())
            .orElseThrow(() -> new UsernameNotFoundException("Player not found"));
    }
} 