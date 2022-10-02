package com.filerental.filterantal.controller;

import com.filerental.filterantal.command.RentFileCommand;
import com.filerental.filterantal.dto.ClientDTO;
import com.filerental.filterantal.dto.TokenKeyPair;
import com.filerental.filterantal.model.Client;
import com.filerental.filterantal.model.File;
import com.filerental.filterantal.service.ClientService;
import com.filerental.filterantal.service.FileService;
import com.filerental.filterantal.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("")
    public ClientDTO save(@RequestBody Client client){
        return new ClientDTO(clientService.saveClient(client));
    }


}
