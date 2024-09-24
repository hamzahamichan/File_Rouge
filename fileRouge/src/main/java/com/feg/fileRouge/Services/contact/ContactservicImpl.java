package com.feg.fileRouge.Services.contact;


import com.feg.fileRouge.Entity.Dto.ContactDto;
import com.feg.fileRouge.Entity.Model.Contact;
import com.feg.fileRouge.Repository.ContactRepository;
import com.feg.fileRouge.mapper.ContactMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactservicImpl implements Contactservice {

    private final ContactRepository contactRepository;
    private final ContactMapper mapper;

    public ContactservicImpl(ContactRepository contactRepository, ContactMapper mapper) {
        this.contactRepository = contactRepository;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<Contact> contacter(ContactDto contactDto) {
        Contact contact = mapper.toEntity(contactDto);
        Contact savedContact = contactRepository.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }
}
