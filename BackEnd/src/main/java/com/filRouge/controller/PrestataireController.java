package com.filRouge.controller;

import com.filRouge.dto.PrestataireRequestDTO;
import com.filRouge.dto.PrestataireResponseDTO;
import com.filRouge.model.Prestataire;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.service.PrestataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestataires")
public class PrestataireController {

    @Autowired
    private PrestataireService prestataireService;

    @PostMapping("/inscription")
    public PrestataireResponseDTO createPrestataire(@RequestBody PrestataireRequestDTO prestataireRequestDTO) {
        return prestataireService.createPrestataire(prestataireRequestDTO);
    }

    @GetMapping("/allPrestataires")
    public List<PrestataireResponseDTO> getAllPrestataires() {
        return prestataireService.getAllPrestataires();
    }

    @GetMapping("/{id}")
    public PrestataireResponseDTO getPrestataireById(@PathVariable Long id) {
        return prestataireService.findById(id);
    }

    @PutMapping("/update/{id}")
    public PrestataireResponseDTO updatePrestataire(@PathVariable Long id, @RequestBody PrestataireRequestDTO prestataireRequestDTO) {
        return prestataireService.updatePrestataire(id, prestataireRequestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePrestataire(@PathVariable Long id) {
        prestataireService.deletePrestataire(id);
    }


    /**
     * Récupérer tous les prestataires en attente de validation.
     *
     * @return Liste des prestataires en attente
     */
    @GetMapping("/enAttente")
    public List<Prestataire> getPendingPrestataires() {
        return prestataireService.getPendingPrestataires();
    }


    /**
     * Vérifier un prestataire.
     *
     * @param id ID du prestataire à vérifier
     * @param status Statut de validation
     * @return Le prestataire vérifié
     */
    @PostMapping("/verify/{id}")
    public ResponseEntity<Prestataire> verifyPrestataire(@PathVariable Long id, @RequestParam ValidateStatus status) {
        Prestataire prestataire = prestataireService.verifyPrestataire(id, status);
        return ResponseEntity.ok(prestataire);
    }

}
