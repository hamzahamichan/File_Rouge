package com.feg.fileRouge.Controller;

import com.feg.fileRouge.Entity.Dto.SallesDto;
import com.feg.fileRouge.Entity.Dto.ServiceDto;
import com.feg.fileRouge.Entity.Model.Service;
import com.feg.fileRouge.Services.servicces.ServicesserviceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    private final ServicesserviceImp service;

    public ServicesController(ServicesserviceImp service) {

        this.service = service;
    }


    @PostMapping("/ajouter/{idSalle}")
    public ResponseEntity<Service> addService(@PathVariable Long idSalle, @RequestBody ServiceDto dto) {
        dto.setIdSalle(idSalle);
        return this.service.addService(dto);
    }


    @GetMapping("/afficher")
    public ServiceDto findServiceId(@RequestParam Long id) {
        return null;
    }

    @GetMapping("/bynom")
    public ServiceDto getServiceByName(@RequestParam String nom) {
        return null;
    }

    @PutMapping("/modifier")
    public ServiceDto UpdateService(@RequestBody SallesDto dto) {
        return null;
    }

    @GetMapping("/afficherAll")
    public List<Service> getAllService() {
        return this.service.getAllService();
    }

    @DeleteMapping
    public void deleteService(@RequestParam Long id){

        this.service.delete(id);
    }
}
