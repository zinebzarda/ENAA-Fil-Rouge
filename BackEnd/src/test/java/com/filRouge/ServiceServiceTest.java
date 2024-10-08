package com.filRouge;


import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Prestataire;
import com.filRouge.model.Services;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.repository.ServiceRepository;
import com.filRouge.service.ServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceServiceTest {

    @InjectMocks
    private ServiceService serviceService;

    @Mock
    private ServiceRepository serviceRepository;

    private Services service;
    private Prestataire prestataire;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        service = new Services();
        service.setId(1L);
        service.setTitre("Test Service");
        service.setDescription("Test Description");
        service.setPrix((float) 100.0);

        prestataire = new Prestataire();
        prestataire.setId(1L);
        prestataire.setValidateStatus(ValidateStatus.EN_ATTENTE);
    }

    @Test
    void createService() {
        Prestataire prestataire = new Prestataire();
        prestataire.setValidateStatus(ValidateStatus.EN_ATTENTE);

        Services service = new Services();
        service.setTitre("Test Service");
        service.setDescription("Service Description");
        service.setPrix(100.0f);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            serviceService.createService(service);
        });

        assertEquals("Prestataire en attente de validation", exception.getMessage());
    }


    @Test
    void updateService() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));
        when(serviceRepository.save(any(Services.class))).thenReturn(service);

        Services updatedService = serviceService.updateService(1L, service);

        assertNotNull(updatedService);
        assertEquals("Test Service", updatedService.getTitre());
    }

    @Test
    void deleteService() {
        doNothing().when(serviceRepository).deleteById(1L);

        assertDoesNotThrow(() -> serviceService.deleteService(1L));
    }

    @Test
    void findById_shouldReturnService() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));

        Optional<Services> foundService = serviceService.findById(1L);

        assertTrue(foundService.isPresent());
        assertEquals("Test Service", foundService.get().getTitre());
    }


}

