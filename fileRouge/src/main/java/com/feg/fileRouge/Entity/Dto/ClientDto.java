package com.feg.fileRouge.Entity.Dto;

import com.feg.fileRouge.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long idClient;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
}
