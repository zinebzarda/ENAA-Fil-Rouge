package com.filRouge.service;

import com.filRouge.dto.PrestataireDTO;
import com.filRouge.model.Prestataire;
import com.filRouge.model.enums.Role;
import com.filRouge.repository.PrestataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PrestataireService {


    @Autowired
    private PrestataireRepository prestataireRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Prestataire createPrestataire(Prestataire prestataire) {
        prestataire.setPassword(passwordEncoder.encode(prestataire.getPassword()));
        prestataire.setRole(Role.PRESTATAIRE);
        return prestataireRepository.save(prestataire);
    }
}
