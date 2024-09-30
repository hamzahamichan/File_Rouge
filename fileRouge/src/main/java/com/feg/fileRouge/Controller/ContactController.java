package com.feg.fileRouge.Controller;

import com.feg.fileRouge.Entity.Dto.ContactDto;
import com.feg.fileRouge.Entity.Model.Contact;
import com.feg.fileRouge.Services.contact.ContactservicImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/cn")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@ControllerAdvice
public class ContactController {

    @Autowired
    private  ContactservicImpl contactservic;

    @PostMapping("/contacte")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Contact> save(@RequestBody ContactDto contactDto){
     return  this.contactservic.contacter(contactDto);
    }
}
