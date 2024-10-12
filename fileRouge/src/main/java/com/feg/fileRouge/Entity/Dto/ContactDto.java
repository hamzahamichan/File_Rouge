package com.feg.fileRouge.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private  Long id_contact;
    private String firstName;
    private String lastName;
    private  String email;
    private String phone;
    private String message;
}
