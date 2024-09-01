package com.filRouge.service;

import com.filRouge.model.*;
import com.filRouge.model.enums.Role;
import com.filRouge.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private DemandeServiceRepository demandeServiceRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Client createClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRole(Role.CLIENT);
        return clientRepository.save(client);
    }
//
//    public Optional<Client> findById(Long id) {
//        return clientRepository.findById(id);
//    }
//
//    public void deleteClient(Long id) {
//        clientRepository.deleteById(id);
//    }
//
//    public List<Client> getAllClients() {
//        return clientRepository.findAll();
//    }
//
//
//public List<Services> rechercherServices(String keyword) {
//        if (keyword != null && !keyword.isEmpty()) {
//            return serviceRepository.findByTitreContainingrDescriptionContaining(keyword, keyword);
//        }
//        return serviceRepository.findAll();
//    }
//
//    public DemandeService demanderService(Long clientId, Long serviceId) {
//        Client client = clientRepository.findById(clientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé"));
//        Services service = serviceRepository.findById(serviceId)
//                .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé"));
//
//        DemandeService demandeService = new DemandeService();
//        demandeService.setClient(client);
//        demandeService.setService(service);
//        demandeService.setDateDemmande(new Date());
//        demandeService.setStatut(StatutService.valueOf("EN_ATTENTE"));
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
}