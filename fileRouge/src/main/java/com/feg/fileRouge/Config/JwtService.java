package com.feg.fileRouge.Config;

import com.feg.fileRouge.Entity.Model.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    // Utilisez une clé de longueur adéquate pour une meilleure sécurité
    private static final String SECRET = "Y+kjOQnyHDkj3ImBnIyWhRb7EKS42SF006Ihgp52Gf4slo/tMig13xfGbe+eBuUkoegTdvduViJ6V3S/ujipTTMOoRSOn/dcMf7WVUddirt1wxYNitOlA9Sn7g/Kk8jYHVDyGZLB/ktfs+URA684qNGmLtZkiu/Emo3F7RfbyhH2QIAXiZ5NE+gZA9EWzo6eJTHEAVjsr0Uf8DF5ZRZ5/KMcKshjrOHMdFEmdVJHTFCuXYkSAe5gPLOfLXwtHwyKlL0WJJjf+qegpHMjnXuWfhyZhweX/sptHARzzF9PJ/Aix3l0KEFkqqiFdxFfL9kxfcLeEn61dsyET246pmfxOVH6VLHsItowUiyd1VP8Muc=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Générer un token JWT
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Long idClient) {
        Client client = (Client) userDetails; // Assurez-vous que userDetails est une instance de Client
        // Ajouter les claims supplémentaires
        extraClaims.put("idClient", idClient);
        extraClaims.put("nom", client.getNom()); // Récupère le nom
        extraClaims.put("prenom", client.getPrenom()); // Récupère le prénom
        extraClaims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()); // Convertir les rôles en liste de chaînes

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)) // 30 jours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    // Extraire tous les claims du token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extraire un claim spécifique
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, Long idClient) {
        Map<String, Object> extraClaims = new HashMap<>();
        return generateToken(extraClaims, userDetails, idClient);
    }

    // Valider le token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Récupérer la clé de signature
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extraire les rôles du token
    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> (List<String>) claims.get("roles"));
    }
}
