package com.filerental.filterantal.service;

import com.filerental.filterantal.model.Client;
import com.filerental.filterantal.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client saveClient(Client client){
        return clientRepository.save(client);
    }
}
