package com.filRouge.service;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Client;
import com.filRouge.model.DemandeService;
import com.filRouge.model.Services;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.repository.DemandeServiceRepository;
import com.filRouge.repository.ClientRepository;
import com.filRouge.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Transactional
    public DemandeService createDemandeService(DemandeService demandeService) {

        var username = SecurityContextHolder.getContext().getAuthentication().getName();

        Client client = clientRepository.findByUsername(username);

        demandeService.setClient(client);

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

    public void deleteDemande(Long id) {
        DemandeService demandeService = demandeServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande non trouvée avec l'id : " + id));
        demandeServiceRepository.delete(demandeService);
    }
}
