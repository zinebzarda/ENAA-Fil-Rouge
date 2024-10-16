package com.filRouge.controller;

import com.filRouge.dto.ClientRequestDTO;
import com.filRouge.dto.ClientResponseDTO;
import com.filRouge.model.DemandeService;
import com.filRouge.model.Services;
import com.filRouge.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Créer un client
    @PostMapping("/inscription")
    public ClientResponseDTO createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.createClient(clientRequestDTO);
    }

    // Obtenir tous les clients
    @GetMapping("/allClients")
    public List<ClientResponseDTO> getAllClients() {
        return clientService.getAllClients();
    }

    // Obtenir un client par ID
    @GetMapping("/{id}")
    public ClientResponseDTO getClientById(@PathVariable Long id) {
        Optional<ClientResponseDTO> clientResponseDTO = clientService.findById(id);
        return clientResponseDTO.orElse(null); // Gérer le cas où le client n'est pas trouvé
    }

    // Mettre à jour un client
    @PutMapping("/update/{id}")
    public ClientResponseDTO updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.updateClient(id, clientRequestDTO);
    }

    // Supprimer un client
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

//    // Rechercher des services
//    @GetMapping("/search")
//    public List<Services> rechercherServices(@RequestParam(required = false) String keyword) {
//        return clientService.rechercherServices(keyword);
//    }
//
//    // Demander un service
//    @PostMapping("/{clientId}/demandeService/{serviceId}")
//    public DemandeService demanderService(@PathVariable Long clientId, @PathVariable Long serviceId) {
//        return clientService.demanderService(clientId, serviceId);
//    }
}
