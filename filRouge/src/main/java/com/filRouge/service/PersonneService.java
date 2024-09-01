package com.filRouge.service;

import com.filRouge.model.Client;
import com.filRouge.model.Personne;
import com.filRouge.model.Prestataire;
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


//    public Personne inscrire(Personne personne , Role role) {
//        personne.setPassword(passwordEncoder.encode(personne.getPassword()));
//        personne.setRole(role);
//        return personneRepository.save(personne);
//    }

    public Personne inscrire(Personne personne, Role role) {
        // Encode le mot de passe avant de le stocker
        personne.setPassword(passwordEncoder.encode(personne.getPassword()));
        personne.setRole(role);

        // Enregistrer le prestataire ou le client selon le type de la personne
        if (personne instanceof Prestataire) {
            Prestataire prestataire = (Prestataire) personne;
            return personneRepository.save(prestataire);
        } else if (personne instanceof Client) {
            Client client = (Client) personne;
            return personneRepository.save(client);
        } else {
            throw new IllegalArgumentException("Type de personne non supporté");
        }
    }




}
