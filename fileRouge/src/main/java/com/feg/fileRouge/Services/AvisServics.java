package com.feg.fileRouge.Services;

import com.feg.fileRouge.Entity.Dto.AvisDto;
import com.feg.fileRouge.Entity.Model.Avis;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AvisServics {

    ResponseEntity<Avis> addAvis(AvisDto dto);
    List<Avis> getAll();
}
