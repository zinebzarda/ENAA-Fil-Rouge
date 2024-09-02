package com.filRouge.controller;

import com.filRouge.model.Client;
import com.filRouge.model.DemandeService;
import com.filRouge.model.Services;
import com.filRouge.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/inscription")
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/allClients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.findById(id)
                .orElse(null);
    }

    @PutMapping("/update/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientService.updateClient(id, clientDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/search")
    public List<Services> rechercherServices(@RequestParam(required = false) String keyword) {
        return clientService.rechercherServices(keyword);
    }

    @PostMapping("/{clientId}/demandeService/{serviceId}")
    public DemandeService demanderService(@PathVariable Long clientId, @PathVariable Long serviceId) {
        return clientService.demanderService(clientId, serviceId);
    }

}
