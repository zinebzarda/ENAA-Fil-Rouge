package com.filRouge.controller;

import com.filRouge.exception.ResourceNotFoundException;
import com.filRouge.model.DemandeService;
import com.filRouge.model.Services;
import com.filRouge.model.enums.ValidateStatus;
import com.filRouge.service.DemandeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandes")
public class DemandeServiceController {

    @Autowired
    private DemandeServiceService demandeServiceService;

    @PostMapping("/add")
    public DemandeService createService(@RequestBody DemandeService service) {
        return demandeServiceService.createDemandeService(service);
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


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
        demandeServiceService.deleteDemande(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
