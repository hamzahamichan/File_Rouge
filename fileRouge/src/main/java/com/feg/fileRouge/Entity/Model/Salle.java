package com.feg.fileRouge.Entity.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity @Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalle;
    private String nom;
    private String description;
    private int capacite;
    private String emplacement;
    private Double prix;

    @OneToMany(mappedBy = "salle")
    private List<Equipement> equipements;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tarif_id", referencedColumnName = "idTarif")
    private Tarif tarif;

    @OneToMany(mappedBy = "salle")
    private List<Avis> avis;

    @OneToMany(mappedBy = "salle")
    private List<Reservation> reservations;
}
