package com.feg.fileRouge.Services.salle;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Salle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalleService {

 ResponseEntity<Salle> save(SallesDto dto);

 SallesDto updateSalle(SallesDto dto);

 ResponseEntity<Void> deleteSalle(Long id);

 SallesDto getSalleById(Long id);

 List<Salle> getAllSalle();
 // Méthode pour récupérer toutes les salles
 List<Salle> getAllSalles(Long idClient);// Ajoutez idClient ici

 Long compterSalles(Long idClient);

 List<Salle> searchSalles(String nom, String emplacement);
}
