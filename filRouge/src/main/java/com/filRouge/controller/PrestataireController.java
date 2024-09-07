package com.filRouge.controller;

import com.filRouge.dto.PrestataireDTO;
import com.filRouge.model.Prestataire;
import com.filRouge.service.PrestataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Prestataires")
public class PrestataireController {

    @Autowired
    private PrestataireService prestataireService;

    @PostMapping("/inscription")
    public Prestataire createPrestataire(@RequestBody Prestataire prestataire) {
        return prestataireService.createPrestataire(prestataire);
    }
}
