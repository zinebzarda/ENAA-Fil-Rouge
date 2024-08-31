package com.filRouge.repository;

import com.filRouge.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
}
