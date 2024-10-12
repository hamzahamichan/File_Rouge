package com.feg.fileRouge.Controller;

import com.feg.fileRouge.Entity.Dto.ClientDto;
import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Salle;
import com.feg.fileRouge.Services.ClientService;
import com.feg.fileRouge.Services.salle.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/")
public class ClientController {

    private final SalleService salleService;


    @Autowired
    private ClientService service;

    public ClientController(SalleService salleService) {
        this.salleService = salleService;
    }

    @PostMapping("/faire/{idClient}")
    public ResponseEntity<Salle> createSalle(@RequestBody SallesDto dto, @PathVariable Long idClient) {
        dto.setIdClient(idClient);
        return salleService.save(dto);
    }

    // Méthode pour récupérer les salles d'un client spécifique
    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Salle>> getAllSallesByClient(@PathVariable Long idClient) {
        List<Salle> salles = salleService.getAllSalles(idClient);
        return ResponseEntity.ok(salles);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long id) {
        return this.salleService.deleteSalle(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SallesDto> updateSalle(@PathVariable Long id, @RequestBody SallesDto dto) {
        dto.setIdSalle(id); // Assurez-vous que l'ID de la salle est mis à jour
        SallesDto updatedSalle = salleService.updateSalle(dto);
        return ResponseEntity.ok(updatedSalle);
    }



}
