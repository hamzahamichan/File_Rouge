package com.feg.fileRouge.Entity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feg.fileRouge.Enum.StatutOfSalle;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalle;
    private String nom;
    private String description;
    private int capacite;
    private String emplacement;
    private Double prix;
    @ElementCollection
    private List<String> services;

    @Enumerated(EnumType.STRING)
    private StatutOfSalle statut;

    @OneToMany(mappedBy = "salle")
    @JsonIgnore
    private List<Equipement> equipements;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tarif_id", referencedColumnName = "idTarif")
    private Tarif tarif;

    @OneToMany(mappedBy = "salle")
    private List<Avis> avis;

    @OneToMany(mappedBy = "salle")
    @JsonIgnore
    private List<Reservation> reservations;



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_client")
    @ToString.Exclude
    private Client client;

}
