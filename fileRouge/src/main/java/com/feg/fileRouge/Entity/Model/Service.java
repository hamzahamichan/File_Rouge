package com.feg.fileRouge.Entity.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;
    private String nom;
    private String description;
    private Long idSalle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tarif_id", referencedColumnName = "idTarif")
    private Tarif tarif;

}
