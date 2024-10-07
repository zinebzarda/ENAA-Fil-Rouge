package com.filRouge.service;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Prestataire;
import com.filRouge.model.Services;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.repository.PrestataireRepository;
import com.filRouge.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {



    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PrestataireRepository prestataireRepository;


    @Transactional
    public Services createService(Services service) {
       var username = SecurityContextHolder.getContext().getAuthentication().getName();
        Prestataire prestataire = prestataireRepository.findByUsername(username);
        if (prestataire.getValidateStatus().equals(ValidateStatus.EN_ATTENTE)) {
            service.setPrestataire(prestataire);
            return serviceRepository.save(service);
        }
        System.out.println("Prestataire en attente de validation");
        return null;
    }

    public Optional<Services> findById(Long id) {
        return serviceRepository.findById(id);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }



    public Services updateService(Long id, Services serviceDetails) {
        Services existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé avec l'id : " + id));

        existingService.setTitre(serviceDetails.getTitre());
        existingService.setDescription(serviceDetails.getDescription());
        existingService.setPrix(serviceDetails.getPrix());
        return serviceRepository.save(existingService);
    }

    public List<Services> searchServices(String titre) {
        return serviceRepository.findByTitreContaining(titre);
    }
}

