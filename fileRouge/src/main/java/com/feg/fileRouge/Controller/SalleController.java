package com.feg.fileRouge.Controller;


import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Salle;
import com.feg.fileRouge.Services.salle.SalleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/salles")
public class SalleController {


    private final SalleServiceImpl service;

    public SalleController(SalleServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/ajouter")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Salle> save(@RequestBody SallesDto dto) {
        try {
            System.out.println("DTO reçu: " + dto);
            return this.service.save(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/modifier")
    public SallesDto updateSalle(@RequestBody SallesDto dto) {
     return  this.service.updateSalle(dto);
    }

    @DeleteMapping("/supprimer")
    public void deleteSalle(@RequestParam Long id) {
        try {
            this.service.deleteSalle(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/salle")
    public SallesDto getSalleById(@RequestParam Long id) {
            return this.service.getSalleById(id);
    }


    @GetMapping("/salles")
    public List<Salle> getAllSalles() {
        return this.service.getAllSalles();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterSalles() {
        try {
            Long nombreSalles = service.compterSalles();
            return ResponseEntity.ok(nombreSalles);
        } catch (Exception e) {
            // En cas d'erreur, on renvoie un statut 500 avec un message d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Salle>> searchSalle(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String emplacement) {

        try {
            List<Salle> salles = service.searchSalles(nom, emplacement);
            if (salles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(salles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
