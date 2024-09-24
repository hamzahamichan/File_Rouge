package com.feg.fileRouge.mapper;

import com.feg.fileRouge.Entity.Dto.ContactDto;
import com.feg.fileRouge.Entity.Model.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDto toDto(Contact contact);
    Contact toEntity(ContactDto contactDto);
}
