package com.feg.fileRouge.Controller;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Salle;
import com.feg.fileRouge.Services.salle.SalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles/")
public class SalleController {

    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping("/salle/{id}")
    public ResponseEntity<SallesDto> getSalleById(@PathVariable Long id) {
        try {
            SallesDto salleDto = salleService.getSalleById(id);
            return ResponseEntity.ok(salleDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("all/{id}")
    public ResponseEntity<List<Salle>> getAllSalles(@RequestParam Long idClient) {
        List<Salle> salles = salleService.getAllSalles(idClient);
        return ResponseEntity.ok(salles);
    }

    @GetMapping("/salles")
    public List<Salle> getAllSalles() {
        return this.salleService.getAllSalle();
    }

    @GetMapping("/count/{idClient}")
    public ResponseEntity<Long> compterSalles(@PathVariable Long idClient) {
        Long count = salleService.compterSalles(idClient); // Passer l'idClient à la méthode
        return ResponseEntity.ok(count);
    }

    // Méthode pour récupérer les salles d'un client spécifique
    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Salle>> getAllSallesByClient(@PathVariable Long idClient) {
        List<Salle> salles = salleService.getAllSalles(idClient);
        return ResponseEntity.ok(salles);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Salle>> searchSalles(@RequestParam(required = false) String nom,
                                                    @RequestParam(required = false) String emplacement) {
        List<Salle> salles = salleService.searchSalles(nom, emplacement);
        return ResponseEntity.ok(salles);
    }
}
