package com.filRouge.service;

import com.filRouge.model.Personne;
import com.filRouge.model.enums.Role;
import com.filRouge.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonneService {
    @   Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Personne inscrire(Personne personne , Role role) {
        personne.setPassword(passwordEncoder.encode(personne.getPassword()));
        personne.setRole(role);
        return personneRepository.save(personne);
    }




}
