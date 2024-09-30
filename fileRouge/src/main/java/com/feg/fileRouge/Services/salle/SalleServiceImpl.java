package com.feg.fileRouge.Services.salle;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Model.Salle;
import com.feg.fileRouge.Enum.StatutOfSalle;
import com.feg.fileRouge.Repository.SalleRepository;
import com.feg.fileRouge.mapper.SalleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleServiceImpl implements SalleService {

        private final SalleMapper salleMapper;
        private final SalleRepository salleRepository;

        @Autowired
        public SalleServiceImpl(SalleMapper salleMapper, SalleRepository salleRepository) {
            this.salleMapper = salleMapper;
            this.salleRepository = salleRepository;
        }

    @Override
    public ResponseEntity<Salle> save(SallesDto dto) {
        System.out.println("DTO avant conversion: " + dto);
        Salle salle = salleMapper.toEntity(dto);
        salle.setStatut(StatutOfSalle.Desponible);// Convertit le DTO en entité
        System.out.println("Entité Salle créée: " + salle); // Log de l'entité avant sauvegarde
        Salle savedSalle = salleRepository.save(salle); // Sauvegarde l'entité en base de données
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSalle); // Retourne la réponse avec le statut 201
    }



        @Override
        public SallesDto updateSalle(SallesDto dto) {
            Salle salleEntity = this.salleMapper.toEntity(dto);
            Salle updatedSalle = this.salleRepository.save(salleEntity);
            return this.salleMapper.toDto(updatedSalle);
        }

        @Override
        public void deleteSalle(Long id) {
            salleRepository.deleteById(id);
        }

    @Override
    public SallesDto getSalleById(Long id) {
        Optional<Salle> salle = salleRepository.findById(id);
        if (salle.isPresent()) {
            SallesDto salleDto = salleMapper.toDto(salle.get());
            System.out.println("DTO généré: " + salleDto);  // Debug
            return salleDto;
        } else {
            return null;
        }
    }


    @Override
        public List<Salle> getAllSalles() {
            List<Salle> list = salleRepository.findAll();
            if (list.isEmpty()){
                throw new RuntimeException("Liste n'existe pas");
            }else
                 return list;
        }

    @Override
    public Long compterSalles() {
        try {
            Long nombre = salleRepository.count(); // Utilisez Long ici pour correspondre au type de retour
            return nombre;
        } catch (Exception e) {

            throw new RuntimeException("Erreur lors du comptage des salles.", e);
        }
    }

    @Override
    public List<Salle> searchSalles(String nom,String emplacement) {
            try {
                return  this.salleRepository.findByCriteria(nom,emplacement);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

}


