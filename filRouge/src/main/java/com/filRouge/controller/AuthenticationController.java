package com.filRouge.controller;

import com.filRouge.model.Personne;
import com.filRouge.model.enums.Role;
import com.filRouge.repository.PersonneRepository;
import com.filRouge.security.JwtAuth;
import com.filRouge.service.PersonneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    PersonneService personneService;


    @PostMapping("/connexion")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Personne authenticationRequest) {
        System.out.println("//////////////////auuuuuthcon");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        Personne personne = personneRepository.findByUsername(authenticationRequest.getUsername());
        Role role= personne.getRole();
        String token = JwtAuth.generateToken(authenticationRequest.getUsername(),role);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/inscription")
    public ResponseEntity<Personne> inscrire(@RequestBody Personne personRequest) {
        Role role = Role.valueOf(String.valueOf(personRequest.getRole()));
        Personne personne = new Personne();
        personne.setUsername(personRequest.getUsername());
        personne.setEmail(personRequest.getEmail());
        personne.setPassword(personRequest.getPassword());
        Personne nouvellePersonne = personneService.inscrire(personne, role);
        return ResponseEntity.ok(nouvellePersonne);
    }
}

