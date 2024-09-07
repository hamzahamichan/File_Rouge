package com.feg.fileRouge.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
    private  static final String SECRET = "Y+kjOQnyHDkj3ImBnIyWhRb7EKS42SF006Ihgp52Gf4slo/tMig13xfGbe+eBuUkoegTdvduViJ6V3S/ujipTTMOoRSOn/dcMf7WVUddirt1wxYNitOlA9Sn7g/Kk8jYHVDyGZLB/ktfs+URA684qNGmLtZkiu/Emo3F7RfbyhH2QIAXiZ5NE+gZA9EWzo6eJTHEAVjsr0Uf8DF5ZRZ5/KMcKshjrOHMdFEmdVJHTFCuXYkSAe5gPLOfLXwtHwyKlL0WJJjf+qegpHMjnXuWfhyZhweX/sptHARzzF9PJ/Aix3l0KEFkqqiFdxFfL9kxfcLeEn61dsyET246pmfxOVH6VLHsItowUiyd1VP8Muc=\n";
  public  String extractUsername(String token){
      return extractClaim(token,Claims::getSubject);
  }

  // Générer un token JWT
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
      return Jwts.builder()
              .setClaims(extraClaims)
              .setSubject(userDetails.getUsername())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 24 minutes
              .signWith(getSigningKey(), SignatureAlgorithm.HS256)  // Nouvelle méthode signWith
              .compact();
  }

  //1st
  private Claims extractAllClaims(String token){
      return Jwts
              .parserBuilder()
              .setSigningKey(getSigningKey())
              .build()
              .parseClaimsJws(token)
              .getBody();
  }
//3rd
  private<T> T extractClaim(String token, Function<Claims,T> claimsResolver){
     final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails){
      return generateToken(new HashMap<>(),userDetails);
  }

  //valider le token
    public  boolean isTokenValid(String token,UserDetails userDetails){
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
  }

    private boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
      return extractClaim(token,Claims::getExpiration);
    }

    //2nd
    private Key getSigningKey() {
      byte[] keyBytes= Decoders.BASE64.decode(SECRET);
      return Keys.hmacShaKeyFor(keyBytes);
    }

}
