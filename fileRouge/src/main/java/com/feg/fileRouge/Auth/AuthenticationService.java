package com.feg.fileRouge.Auth;

import com.feg.fileRouge.Config.JwtService;
import com.feg.fileRouge.Entity.Model.Client;
import com.feg.fileRouge.Enum.Role;
import com.feg.fileRouge.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Méthode pour enregistrer un nouveau client
    public AuthenticationResponse register(RegisterRequest request) {

        var client = Client.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .numberPhone(request.getNumberPhone())
                .email(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .role(request.getRole())
                .build();

        // Sauvegarder le client dans la base de données
        clientRepository.save(client);
        // Générer le token JWT en passant l'ID du client
        var jwtToken = jwtService.generateToken(new HashMap<>(), client, client.getIdClient());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    // Méthode pour authentifier un client
    public AuthenticationResponse authenticate(AutheticateRequest request) {
        // Authentifier l'utilisateur avec l'email et le mot de passe
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse()));
        var client = clientRepository.findByEmail(request.getEmail()).orElseThrow();
        // Générer le token JWT en passant l'ID du client
        var jwtToken = jwtService.generateToken(new HashMap<>(), client, client.getIdClient());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    // Vérifier si l'email existe dans la base de données
    public boolean emailExists(String email) {
        return clientRepository.findByEmail(email).isPresent();
    }
}

