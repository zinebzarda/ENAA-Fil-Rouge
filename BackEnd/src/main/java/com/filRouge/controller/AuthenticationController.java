package com.filRouge.controller;

import com.filRouge.dto.LoginDTO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    PersonneService personneService;


    @PostMapping("/connexion")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        Personne personne = personneRepository.findByUsername(authenticationRequest.getUsername());
        Role role= personne.getRole();
        String token = JwtAuth.generateToken(authenticationRequest.getUsername(), personne.getId(), role);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);

    }

}
