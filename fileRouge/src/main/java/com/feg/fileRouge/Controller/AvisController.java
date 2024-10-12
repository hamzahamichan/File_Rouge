package com.feg.fileRouge.Controller;


import com.feg.fileRouge.Entity.Dto.AvisDto;
import com.feg.fileRouge.Entity.Model.Avis;
import com.feg.fileRouge.Services.AvisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/avis")
public class AvisController {
    @Autowired
    private AvisServiceImpl avisService;

    @PostMapping("/add")
    public ResponseEntity<Avis> addAvis(@RequestBody AvisDto avis){
       try {
           return this.avisService.addAvis(avis);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
