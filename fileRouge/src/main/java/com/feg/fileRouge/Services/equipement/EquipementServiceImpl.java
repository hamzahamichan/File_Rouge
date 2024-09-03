package com.feg.fileRouge.Services.equipement;

import com.feg.fileRouge.Entity.Dto.EquipementDto;
import com.feg.fileRouge.Entity.Model.Equipement;
import com.feg.fileRouge.Repository.EquipementRepository;
import com.feg.fileRouge.mapper.EquipementMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementServiceImpl implements EquipementService {
    private final EquipementRepository equipementRepository;
    private final EquipementMapper mapper;

    public EquipementServiceImpl(EquipementRepository equipementRepository, EquipementMapper mapper) {
        this.equipementRepository = equipementRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Equipement> save(EquipementDto equipementDto) {
        Equipement equipement = mapper.toEntity(equipementDto); // Convertit le DTO en entité
        Equipement equipement1 = equipementRepository.save(equipement); // Sauvegarde l'entité en base de données
        return ResponseEntity.status(HttpStatus.CREATED).body(equipement1); // Retourne la réponse avec le statut 201
    }

    @Override
    public EquipementDto getEquipementById(Long id) {
        Optional<Equipement> equipement = equipementRepository.findById(id);
        return equipement.map(mapper::toDto).orElse(null);
    }

    @Override
    public List<Equipement> getAllEquipements() {
        return this.equipementRepository.findAll();
    }

    @Override
    public EquipementDto updateEquipement(EquipementDto equipementDto) {
        Equipement entity = this.mapper.toEntity(equipementDto);
        Equipement updatedequi = this.equipementRepository.save(entity);
        return this.mapper.toDto(updatedequi);
    }

    @Override
    public void deleteEquipement(Long id) {
       this.equipementRepository.deleteById(id);
    }
}
