package com.filRouge.service;

import com.filRouge.dto.ClientRequestDTO;
import com.filRouge.dto.ClientResponseDTO;
import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Client;
import com.filRouge.model.DemandeService;
import com.filRouge.model.Services;
import com.filRouge.model.enums.Role;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private DemandeServiceRepository demandeServiceRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        Client client = new Client();
        client.setUsername(clientRequestDTO.getUsername());
        client.setEmail(clientRequestDTO.getEmail());
        client.setAdresse(clientRequestDTO.getAdresse());
        client.setPassword(passwordEncoder.encode(clientRequestDTO.getPassword()));
        client.setRole(Role.CLIENT);

        Client savedClient = clientRepository.save(client);

        return convertToClientResponseDTO(savedClient);
    }


    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::convertToClientResponseDTO)
                .collect(Collectors.toList());
    }


    public Optional<ClientResponseDTO> findById(Long id) {
        return clientRepository.findById(id)
                .map(this::convertToClientResponseDTO);
    }


    public ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id : " + id));

        existingClient.setUsername(clientRequestDTO.getUsername());
        existingClient.setEmail(clientRequestDTO.getEmail());
        existingClient.setAdresse(clientRequestDTO.getAdresse());

        if (clientRequestDTO.getPassword() != null && !clientRequestDTO.getPassword().isEmpty()) {
            existingClient.setPassword(passwordEncoder.encode(clientRequestDTO.getPassword()));
        }

        Client updatedClient = clientRepository.save(existingClient);

        return convertToClientResponseDTO(updatedClient);
    }


    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }


//    public List<Services> rechercherServices(String keyword) {
//        if (keyword != null && !keyword.isEmpty()) {
//            return serviceRepository.findByTitreContainingOrDescriptionContaining(keyword, keyword);
//        }
//        return serviceRepository.findAll();
//    }


//    public DemandeService demanderService(Long clientId, Long serviceId) {
//        Client client = clientRepository.findById(clientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé"));
//        Services service = serviceRepository.findById(serviceId)
//                .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé"));
//
//        DemandeService demandeService = new DemandeService();
//        demandeService.setClient(client);
//        demandeService.setService(service);
//        demandeService.setDateDemmande(LocalDate.now());
//        demandeService.setStatut(ValidateStatus.EN_ATTENTE);
//
//        return demandeServiceRepository.save(demandeService);
//    }




//
//    public Feedback laisserFeedback(Long clientId, Long demandeServiceId, Feedback feedback) {
//        Client client = clientRepository.findById(clientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé"));
//        DemandeService demandeService = demandeServiceRepository.findById(demandeServiceId)
//                .orElseThrow(() -> new ResourceNotFoundException("Demande de service non trouvée"));
//
//        if (!demandeService.getClient().equals(client)) {
//            throw new IllegalArgumentException("Ce client n'est pas autorisé à laisser un feedback pour cette demande");
//        }
//
//        feedback.setDemandeService(demandeService);
//        feedback.setDate_creation(new Date());
//        return feedbackRepository.save(feedback);
//    }

//    public Contact contacterSupport(Long clientId, String message) {
//        Client client = clientRepository.findById(clientId)
//                .orElseThrow(() -> new com.example.GestionRessourcesInfo.exception.ResourceNotFoundException("Client non trouvé"));
//
//        Contact contact = new Contact();
//        contact.setMessage(message);
//        // Supposons que nous avons une relation entre Contact et Client
//        // contact.setClient(client);
//        return clientRepository.save(contact);
//    }



    // Convertir Client en ClientResponseDTO
    private ClientResponseDTO convertToClientResponseDTO(Client client) {
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        clientResponseDTO.setUsername(client.getUsername());
        clientResponseDTO.setEmail(client.getEmail());
        clientResponseDTO.setAdresse(client.getAdresse());
        return clientResponseDTO;
    }
}