package com.filRouge.service;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Client;
import com.filRouge.model.DemandeService;
import com.filRouge.model.Services;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.repository.DemandeServiceRepository;
import com.filRouge.repository.ClientRepository;
import com.filRouge.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeServiceService {

    @Autowired
    private DemandeServiceRepository demandeServiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public DemandeService createDemandeService(Long clientId, Long serviceId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id : " + clientId));

        Services service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé avec l'id : " + serviceId));

        DemandeService demandeService = new DemandeService();
        demandeService.setClient(client);
        demandeService.setService(service);
        demandeService.setDateDemmande(LocalDate.now());
        demandeService.setStatut(ValidateStatus.EN_ATTENTE);

        return demandeServiceRepository.save(demandeService);
    }

    public List<DemandeService> getAllDemandes() {
        return demandeServiceRepository.findAll();
    }

    public Optional<DemandeService> getDemandeById(Long id) {
        return Optional.ofNullable(demandeServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande non trouvée avec l'id : " + id)));
    }

    public DemandeService updateDemande(Long id, Long clientId, Long serviceId, ValidateStatus statut) {
        DemandeService existingDemande = demandeServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande non trouvée avec l'id : " + id));

        if (clientId != null) {
            Client client = clientRepository.findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id : " + clientId));
            existingDemande.setClient(client);
        }

        if (serviceId != null) {
            Services service = serviceRepository.findById(serviceId)
                    .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé avec l'id : " + serviceId));
            existingDemande.setService(service);
        }

        if (statut != null) {
            existingDemande.setStatut(statut);
        }

        return demandeServiceRepository.save(existingDemande);
    }

    public void deleteDemande(Long id) {
        DemandeService demandeService = demandeServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande non trouvée avec l'id : " + id));
        demandeServiceRepository.delete(demandeService);
    }
}
