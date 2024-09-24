package com.filRouge;

import com.filRouge.dto.PrestataireRequestDTO;
import com.filRouge.dto.PrestataireResponseDTO;
import com.filRouge.model.Prestataire;
import com.filRouge.model.enums.Role;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.repository.PrestataireRepository;
import com.filRouge.service.PrestataireService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrestataireServiceTest {

    @Mock
    private PrestataireRepository prestataireRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PrestataireService prestataireService;

    private Prestataire prestataire;
    private PrestataireRequestDTO prestataireRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        prestataire = new Prestataire();
        prestataire.setId(1L);
        prestataire.setUsername("testUser");
        prestataire.setEmail("test@example.com");
        prestataire.setPassword("password");
        prestataire.setDomaineExpertise("IT");
        prestataire.setDisponibilites("Available");
        prestataire.setExperience("experience");
        prestataire.setRole(Role.PRESTATAIRE);
        prestataire.setValidateStatus(ValidateStatus.EN_ATTENTE);

        prestataireRequestDTO = new PrestataireRequestDTO();
        prestataireRequestDTO.setUsername("testUser");
        prestataireRequestDTO.setEmail("test@example.com");
        prestataireRequestDTO.setPassword("password");
        prestataireRequestDTO.setDomaineExpertise("IT");
        prestataireRequestDTO.setDisponibilites("Available");
        prestataireRequestDTO.setExperience("experience");
        prestataireRequestDTO.setStatus(ValidateStatus.EN_ATTENTE);
    }

    @Test
    void testCreatePrestataire() {
        when(passwordEncoder.encode(prestataireRequestDTO.getPassword())).thenReturn("encodedPassword");
        when(prestataireRepository.save(any(Prestataire.class))).thenReturn(prestataire);

        PrestataireResponseDTO response = prestataireService.createPrestataire(prestataireRequestDTO);

        assertNotNull(response);
        assertEquals(prestataire.getUsername(), response.getUsername());
        assertEquals(prestataire.getEmail(), response.getEmail());
        verify(passwordEncoder).encode(prestataireRequestDTO.getPassword());
        verify(prestataireRepository).save(any(Prestataire.class));
    }

    @Test
    void testGetAllPrestataires() {
        when(prestataireRepository.findAll()).thenReturn(Arrays.asList(prestataire));

        List<PrestataireResponseDTO> responseList = prestataireService.getAllPrestataires();

        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals(prestataire.getUsername(), responseList.get(0).getUsername());
        verify(prestataireRepository).findAll();
    }

    @Test
    void testFindById() {
        when(prestataireRepository.findById(1L)).thenReturn(Optional.of(prestataire));

        PrestataireResponseDTO response = prestataireService.findById(1L);

        assertNotNull(response);
        assertEquals(prestataire.getUsername(), response.getUsername());
        verify(prestataireRepository).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(prestataireRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            prestataireService.findById(1L);
        });

        assertEquals("Prestataire non trouvé avec l'id : 1", exception.getMessage());
        verify(prestataireRepository).findById(1L);
    }

    @Test
    void testUpdatePrestataire() {
        when(prestataireRepository.findById(1L)).thenReturn(Optional.of(prestataire));
        when(prestataireRepository.save(any(Prestataire.class))).thenReturn(prestataire);
        prestataireRequestDTO.setExperience("experience");  // Met à jour l'expérience

        PrestataireResponseDTO response = prestataireService.updatePrestataire(1L, prestataireRequestDTO);

        assertNotNull(response);
        assertEquals("experience", response.getExperience());
        verify(prestataireRepository).findById(1L);
        verify(prestataireRepository).save(any(Prestataire.class));
    }

    @Test
    void testUpdatePrestataire_NotFound() {
        when(prestataireRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            prestataireService.updatePrestataire(1L, prestataireRequestDTO);
        });

        assertEquals("Prestataire non trouvé avec l'id : 1", exception.getMessage());
        verify(prestataireRepository).findById(1L);
    }

    @Test
    void testDeletePrestataire() {
        prestataireService.deletePrestataire(1L);
        verify(prestataireRepository).deleteById(1L);
    }

    @Test
    void testGetPendingPrestataires() {
        when(prestataireRepository.findByValidateStatus(ValidateStatus.EN_ATTENTE)).thenReturn(Arrays.asList(prestataire));

        List<Prestataire> pendingPrestataires = prestataireService.getPendingPrestataires();

        assertNotNull(pendingPrestataires);
        assertEquals(1, pendingPrestataires.size());
        verify(prestataireRepository).findByValidateStatus(ValidateStatus.EN_ATTENTE);
    }

    @Test
    void testVerifyPrestataire() {
        when(prestataireRepository.findById(1L)).thenReturn(Optional.of(prestataire));
        when(prestataireRepository.save(prestataire)).thenReturn(prestataire);

        Prestataire updatedPrestataire = prestataireService.verifyPrestataire(1L, ValidateStatus.EN_ATTENTE);

        assertNotNull(updatedPrestataire);
        assertEquals(ValidateStatus.EN_ATTENTE, updatedPrestataire.getValidateStatus());
        verify(prestataireRepository).findById(1L);
        verify(prestataireRepository).save(prestataire);
    }

    @Test
    void testVerifyPrestataire_NotFound() {
        when(prestataireRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            prestataireService.verifyPrestataire(1L, ValidateStatus.EN_ATTENTE);
        });

        assertEquals("Prestataire non trouvé", exception.getMessage());
        verify(prestataireRepository).findById(1L);
    }
}

