package com.feg.fileRouge.Controller;

import com.feg.fileRouge.Entity.Dto.ContactDto;
import com.feg.fileRouge.Entity.Model.Contact;
import com.feg.fileRouge.Services.contact.ContactservicImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
public class ContactController {

    @Autowired
    private  ContactservicImpl contactservic;

    @PostMapping("/contacte")
    public ResponseEntity<Contact> save(@RequestBody ContactDto contactDto){
     return  this.contactservic.contacter(contactDto);
    }
}
