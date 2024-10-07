package com.filRouge.repository;

import com.filRouge.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
   Personne findByUsername(String username);
   Personne findByEmail(String email);
}