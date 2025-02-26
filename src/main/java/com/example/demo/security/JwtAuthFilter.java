    package com.example.demo.security;

    import java.io.IOException;
    import java.nio.charset.StandardCharsets;
    import java.util.Arrays;

    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.web.filter.OncePerRequestFilter;

    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.security.Keys;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;

    /**
     * Filtre qui intercepte les requêtes pour vérifier les JWT
     * Hérite de OncePerRequestFilter pour s'assurer que le filtre n'est exécuté qu'une fois par requête
     */
    public class JwtAuthFilter extends OncePerRequestFilter {

        // Clé secrète pour vérifier les JWT
        private final String jwtSecret;

        /**
         * Constructeur qui initialise la clé secrète
         * @param jwtSecret Clé pour vérifier les signatures JWT
         */
        public JwtAuthFilter(String jwtSecret) {
            this.jwtSecret = jwtSecret;
        }

        /**
         * Méthode principale du filtre qui traite chaque requête
         * @param request Requête HTTP entrante
         * @param response Réponse HTTP
         * @param filterChain Chaîne de filtres
         */
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            
            // Récupère le token du header Authorization
            String token = request.getHeader("Authorization");
            
            if (token != null && token.startsWith("Bearer ")) {
                // Enlève le préfixe "Bearer "
                token = token.substring(7);
                try {
                    // Vérifie et décode le token
                    Claims claims = Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
                    
                    // Récupère le nom d'utilisateur du token
                    String username = claims.getSubject();
                    
                    // Crée un objet d'authentification avec le rôle USER
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
                        
                    // Définit l'authentification dans le contexte de sécurité
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (Exception e) {
                    // En cas de token invalide, renvoie une erreur 401
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
            
            // Continue la chaîne de filtres
            filterChain.doFilter(request, response);
        } 

        /**
         * Détermine si le filtre doit être appliqué à cette requête
         * @param request Requête HTTP
         * @return true si la requête ne doit pas être filtrée
         */
        @Override
        protected boolean shouldNotFilter(HttpServletRequest request) {
            String path = request.getServletPath();
            // Ignore les routes de login et création de compte
            return path.equals("/api/login") || path.equals("/players/create");
        }
    } 