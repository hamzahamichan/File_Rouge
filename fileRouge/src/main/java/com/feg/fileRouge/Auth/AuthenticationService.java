package com.feg.fileRouge.Auth;


import com.feg.fileRouge.Config.JwtService;
import com.feg.fileRouge.Entity.Model.Client;
import com.feg.fileRouge.Enum.Role;
import com.feg.fileRouge.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticationService {

 private final ClientRepository clientRepository;
 private final PasswordEncoder passwordEncoder;
 private final JwtService jwtService;
 private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var client = Client.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .email(request.getEmail())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .role(Role.ADMIN)
                .build();
        clientRepository.save(client);
        var jwttoken = jwtService.generateToken(client);
        return AuthenticationResponse.builder()
                .token(jwttoken)
                .build();
    }

    public AuthenticationResponse authenticate(AutheticateRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getMotDePasse()));
        var client = clientRepository.findByEmail(request.getEmail()).orElseThrow();
         var jwttoken = jwtService.generateToken(client);
         return AuthenticationResponse.builder()
                .token(jwttoken)
                .build();
    }

    public boolean emailExists(String email) {
        return clientRepository.findByEmail(email).isPresent(); // Méthode de votre repository qui vérifie l'existence de l'email
    }
}
