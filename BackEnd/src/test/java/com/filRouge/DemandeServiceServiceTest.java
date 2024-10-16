package com.filRouge;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.Client;
import com.filRouge.model.DemandeService;
import com.filRouge.model.Services;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.repository.ClientRepository;
import com.filRouge.repository.DemandeServiceRepository;
import com.filRouge.repository.ServiceRepository;
import com.filRouge.service.DemandeServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DemandeServiceServiceTest {

    @Mock
    private DemandeServiceRepository demandeServiceRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private DemandeServiceService demandeServiceService;

    private Client client;
    private Services service;
    private DemandeService demandeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test objects
        client = new Client();
        client.setId(1L);

        service = new Services();
        service.setId(1L);

        demandeService = new DemandeService();
        demandeService.setId(1L);
        demandeService.setClient(client);
        demandeService.setService(service);
        demandeService.setDateDemmande(LocalDate.now());
    }

//    @Test
//    void testCreateDemandeService() {
//        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));
//        when(demandeServiceRepository.save(any(DemandeService.class))).thenReturn(demandeService);
//
//        DemandeService createdDemande = demandeServiceService.createDemandeService(1L);
//
//        assertNotNull(createdDemande);
//        assertEquals(client, createdDemande.getClient());
//        assertEquals(service, createdDemande.getService());
//        verify(serviceRepository).findById(1L);
//        verify(demandeServiceRepository).save(any(DemandeService.class));
//    }

    @Test
    void testGetAllDemandes() {
        when(demandeServiceRepository.findAll()).thenReturn(List.of(demandeService));

        var demandes = demandeServiceService.getAllDemandes();

        assertNotNull(demandes);
        assertEquals(1, demandes.size());
        assertEquals(demandeService, demandes.get(0));

        verify(demandeServiceRepository).findAll();
    }

    @Test
    void testGetDemandeById() {
        when(demandeServiceRepository.findById(1L)).thenReturn(Optional.of(demandeService));

        DemandeService foundDemande = demandeServiceService.getDemandeById(1L).get();

        assertNotNull(foundDemande);
        assertEquals(demandeService, foundDemande);

        verify(demandeServiceRepository).findById(1L);
    }

    @Test
    void testGetDemandeByIdNotFound() {
        when(demandeServiceRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            demandeServiceService.getDemandeById(1L);
        });

        assertEquals("Demande non trouvée avec l'id : 1", exception.getMessage());
        verify(demandeServiceRepository).findById(1L);
    }





    @Test
    void testDeleteDemande() {
        when(demandeServiceRepository.findById(1L)).thenReturn(Optional.of(demandeService));

        demandeServiceService.deleteDemande(1L);

        verify(demandeServiceRepository).delete(demandeService);
    }

    @Test
    void testDeleteDemandeNotFound() {
        when(demandeServiceRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            demandeServiceService.deleteDemande(1L);
        });

        assertEquals("Demande non trouvée avec l'id : 1", exception.getMessage());
        verify(demandeServiceRepository).findById(1L);
    }
}
