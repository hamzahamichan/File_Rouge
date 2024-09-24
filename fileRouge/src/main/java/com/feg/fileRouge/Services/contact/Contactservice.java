package com.feg.fileRouge.Services.contact;

import com.feg.fileRouge.Entity.Dto.ContactDto;
import com.feg.fileRouge.Entity.Model.Contact;
import org.springframework.http.ResponseEntity;


public interface Contactservice {
    ResponseEntity<Contact> contacter(ContactDto contactDto);


}
