package com.filRouge.service;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Services;
import com.filRouge.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {



        @Autowired
        private ServiceRepository serviceRepository;

        public Services createService(Services service) {
            return serviceRepository.save(service);
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

        public List<Services> searchServices(String titre) {
            return serviceRepository.findByTitreContaining(titre);
        }

        public Services updateService(Long id, Services serviceDetails) {
            Services existingService = serviceRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Service non trouvé avec l'id : " + id));

            existingService.setTitre(serviceDetails.getTitre());
            existingService.setDescription(serviceDetails.getDescription());
            existingService.setPrix(serviceDetails.getPrix());
            return serviceRepository.save(existingService);
        }
    }


