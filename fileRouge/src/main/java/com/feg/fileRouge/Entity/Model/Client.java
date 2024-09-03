package com.feg.fileRouge.Entity.Model;

import com.feg.fileRouge.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "client")
    private List<Avis> avis;
}
