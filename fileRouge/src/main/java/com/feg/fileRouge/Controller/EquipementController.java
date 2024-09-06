package com.feg.fileRouge.Controller;

import com.feg.fileRouge.Entity.Dto.EquipementDto;
import com.feg.fileRouge.Entity.Model.Equipement;
import com.feg.fileRouge.Services.equipement.EquipementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/equipement")
@RestController
public class EquipementController {
  @Autowired
    private EquipementServiceImpl service;

    @PostMapping(value = "/ajouter")
    ResponseEntity<Equipement> save(@RequestBody EquipementDto equipementDto){
        return this.service.save(equipementDto);
    }
     @GetMapping("/afficher")
    EquipementDto getEquipementById(@RequestParam Long id){

        return this.service.getEquipementById(id);
    }
    @GetMapping("/liste")
    List<Equipement> getAllEquipements(){

        return this.service.getAllEquipements();
    }
    @PutMapping("/modifier")
    EquipementDto updateEquipement(@RequestBody EquipementDto equipementDto){
        return this.service.updateEquipement(equipementDto);
    }
    @DeleteMapping("/supprimer")
    void deleteEquipement(@RequestParam Long id){
        this.service.deleteEquipement(id);
    }
}
