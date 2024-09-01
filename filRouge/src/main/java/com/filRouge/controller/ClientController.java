package com.filRouge.controller;

import com.filRouge.model.Client;
import com.filRouge.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/inscription")
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }
}
