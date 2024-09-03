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


    private  final  SalleServiceImpl service;

    public SalleController(SalleServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/ajouter")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Salle> save(@RequestBody SallesDto dto) {
        // Log du DTO reçu pour vérifier les valeurs
        System.out.println("DTO reçu: " + dto);
        return this.service.save(dto);
    }

//    @PutMapping("/modifier")
//    public SallesDto updateSalle(@RequestBody SallesDto dto) {
//     return  this.service.updateSalle(dto);
//    }

    @DeleteMapping("/supprimer")
    public void deleteSalle(@RequestParam Long id) {
     this.service.deleteSalle(id);
    }

    @GetMapping("/salle")
    public SallesDto getSalleById(@RequestParam Long id) {
      return this.service.getSalleById(id);
    }

    @GetMapping("/salles")
    public List<Salle> getAllSalles() {

      return this.service.getAllSalles();
    }
}
