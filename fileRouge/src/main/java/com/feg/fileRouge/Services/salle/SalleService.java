package com.feg.fileRouge.Services.salle;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Salle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalleService {

       ResponseEntity<Salle> save(SallesDto dto);

        SallesDto updateSalle(SallesDto dto);

        void deleteSalle(Long id);

        SallesDto getSalleById(Long id);

        List<Salle> getAllSalles();

        Long compterSalles();

        List<Salle> searchSalles(String nom, String description, Integer capacite, String emplacement);
}
