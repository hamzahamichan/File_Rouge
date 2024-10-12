package com.feg.fileRouge.Services;

import com.feg.fileRouge.Entity.Dto.ClientDto;
import com.feg.fileRouge.Entity.Model.Client;
import com.feg.fileRouge.Repository.ClientRepository;
import com.feg.fileRouge.mapper.ClientMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService implements ClientServies{

     private final ClientRepository clientRepository;
     private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

   public ClientDto getById(Long id){
        Optional<Client> client =clientRepository.findById(id);
        if (client.isPresent()){
            ClientDto clientDto=clientMapper.toDto(client.get());
            System.out.println("DTO généré: " + clientDto);  // Debug
            return clientDto;
        }else {
            return null;
        }
    }

}
