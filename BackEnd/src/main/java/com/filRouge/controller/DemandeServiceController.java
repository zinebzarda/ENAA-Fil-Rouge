package com.filRouge.controller;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.DemandeService;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.service.DemandeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeServiceController {

    @Autowired
    private DemandeServiceService demandeServiceService;

    @PostMapping
    public ResponseEntity<DemandeService> createDemandeService(@RequestParam Long clientId,
                                                               @RequestParam Long serviceId) {
        DemandeService demandeService = demandeServiceService.createDemandeService(clientId, serviceId);
        return new ResponseEntity<>(demandeService, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DemandeService>> getAllDemandes() {
        List<DemandeService> demandes = demandeServiceService.getAllDemandes();
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeService> getDemandeById(@PathVariable Long id) {
        DemandeService demandeService = demandeServiceService.getDemandeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demande non trouvée avec l'id : " + id));
        return new ResponseEntity<>(demandeService, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeService> updateDemande(@PathVariable Long id,
                                                        @RequestParam(required = false) Long clientId,
                                                        @RequestParam(required = false) Long serviceId,
                                                        @RequestParam(required = false) ValidateStatus statut) {
        DemandeService updatedDemande = demandeServiceService.updateDemande(id, clientId, serviceId, statut);
        return new ResponseEntity<>(updatedDemande, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
        demandeServiceService.deleteDemande(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
