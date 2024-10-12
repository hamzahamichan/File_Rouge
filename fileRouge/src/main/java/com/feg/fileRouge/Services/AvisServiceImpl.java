package com.feg.fileRouge.Services;

import com.feg.fileRouge.Entity.Dto.AvisDto;
import com.feg.fileRouge.Entity.Model.Avis;
import com.feg.fileRouge.Entity.Model.Salle;
import com.feg.fileRouge.Repository.AvisRepository;
import com.feg.fileRouge.Repository.SalleRepository;
import com.feg.fileRouge.mapper.AvisMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AvisServiceImpl implements AvisServics{


    private final AvisRepository avisRepository;
    private final AvisMapper avisMapper;

    public AvisServiceImpl(SalleRepository salleRepository, AvisRepository avisRepository, AvisMapper avisMapper) {
        this.avisRepository = avisRepository;
        this.avisMapper = avisMapper;
    }

    @Override
    public ResponseEntity<Avis>addAvis(AvisDto dto) {
        Avis avis = this.avisMapper.toEnyity(dto);
        Avis dto1=this.avisRepository.save(avis);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto1);
    }

    @Override
    public List<Avis> getAll() {
        List<Avis> list=this.avisRepository.findAll();
        if (list.isEmpty()){
            return null;
        }else {
            return list;
        }
    }


}
