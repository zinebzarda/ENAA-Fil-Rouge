package com.filRouge.service;

import com.filRouge.dto.PrestataireRequestDTO;
import com.filRouge.dto.PrestataireResponseDTO;
import com.filRouge.model.Prestataire;
import com.filRouge.model.enums.Role;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.repository.PrestataireRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestataireService {

    @Autowired
    private PrestataireRepository prestataireRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Création d'un nouveau prestataire
    public PrestataireResponseDTO createPrestataire(PrestataireRequestDTO prestataireRequestDTO) {
        Prestataire prestataire = new Prestataire();
        prestataire.setUsername(prestataireRequestDTO.getUsername());
        prestataire.setEmail(prestataireRequestDTO.getEmail());
        prestataire.setPassword(passwordEncoder.encode(prestataireRequestDTO.getPassword()));
        prestataire.setDomaineExpertise(prestataireRequestDTO.getDomaineExpertise());
        prestataire.setDisponibilites(prestataireRequestDTO.getDisponibilites());
        prestataire.setExperience(prestataireRequestDTO.getExperience());
        prestataire.setTel(prestataireRequestDTO.getTel());
        prestataire.setRole(Role.PRESTATAIRE);
        prestataire.setValidateStatus(ValidateStatus.EN_ATTENTE);

        Prestataire savedPrestataire = prestataireRepository.save(prestataire);

        return convertToPrestataireResponseDTO(savedPrestataire);
    }

    // Récupérer tous les prestataires
    public List<PrestataireResponseDTO> getAllPrestataires() {
        return prestataireRepository.findAll().stream()
                .map(this::convertToPrestataireResponseDTO)
                .collect(Collectors.toList());
    }

    // Trouver un prestataire par ID
    public PrestataireResponseDTO findById(Long id) {
        Prestataire prestataire = prestataireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestataire non trouvé avec l'id : " + id));
        return convertToPrestataireResponseDTO(prestataire);
    }

    // Mise à jour d'un prestataire
    public PrestataireResponseDTO updatePrestataire(Long id, PrestataireRequestDTO prestataireRequestDTO) {
        Prestataire existingPrestataire = prestataireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestataire non trouvé avec l'id : " + id));

        existingPrestataire.setUsername(prestataireRequestDTO.getUsername());
        existingPrestataire.setEmail(prestataireRequestDTO.getEmail());
        existingPrestataire.setDomaineExpertise(prestataireRequestDTO.getDomaineExpertise());
        existingPrestataire.setDisponibilites(prestataireRequestDTO.getDisponibilites());
        existingPrestataire.setExperience(prestataireRequestDTO.getExperience());
        existingPrestataire.setValidateStatus(prestataireRequestDTO.getStatus());
        existingPrestataire.setTel(prestataireRequestDTO.getTel()); // Mise à jour du téléphone

        if (prestataireRequestDTO.getPassword() != null && !prestataireRequestDTO.getPassword().isEmpty()) {
            existingPrestataire.setPassword(passwordEncoder.encode(prestataireRequestDTO.getPassword()));
        }

        Prestataire updatedPrestataire = prestataireRepository.save(existingPrestataire);

        return convertToPrestataireResponseDTO(updatedPrestataire);
    }

    // Suppression d'un prestataire
    public void deletePrestataire(Long id) {
        prestataireRepository.deleteById(id);
    }

    // Conversion de Prestataire vers PrestataireResponseDTO
    private PrestataireResponseDTO convertToPrestataireResponseDTO(Prestataire prestataire) {
        PrestataireResponseDTO prestataireResponseDTO = new PrestataireResponseDTO();
        prestataireResponseDTO.setId(prestataire.getId());
        prestataireResponseDTO.setUsername(prestataire.getUsername());
        prestataireResponseDTO.setEmail(prestataire.getEmail());
        prestataireResponseDTO.setDomaineExpertise(prestataire.getDomaineExpertise());
        prestataireResponseDTO.setDisponibilites(prestataire.getDisponibilites());
        prestataireResponseDTO.setExperience(prestataire.getExperience());
        prestataireResponseDTO.setTel(prestataire.getTel()); // Conversion du téléphone
        prestataireResponseDTO.setStatus(prestataire.getValidateStatus());
        return prestataireResponseDTO;
    }


    public List<Prestataire> getPendingPrestataires() {
        return prestataireRepository.findByValidateStatus(ValidateStatus.EN_ATTENTE);
    }


    public Prestataire verifyPrestataire(Long prestataireId, ValidateStatus status) {
        Prestataire prestataire = prestataireRepository.findById(prestataireId)
                .orElseThrow(() -> new EntityNotFoundException("Prestataire non trouvé"));

        prestataire.setValidateStatus(status);
        return prestataireRepository.save(prestataire);
    }
}
