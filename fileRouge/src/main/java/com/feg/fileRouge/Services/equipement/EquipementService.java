package com.feg.fileRouge.Services.equipement;

import com.feg.fileRouge.Entity.Dto.EquipementDto;
import com.feg.fileRouge.Entity.Model.Equipement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EquipementService {
    ResponseEntity<Equipement> save(EquipementDto equipementDto);

    EquipementDto getEquipementById(Long id);

    List<Equipement> getAllEquipements();

    EquipementDto updateEquipement(EquipementDto equipementDto);

    void deleteEquipement(Long id);
}
