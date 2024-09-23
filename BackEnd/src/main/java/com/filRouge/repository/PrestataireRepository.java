package com.filRouge.repository;

import com.filRouge.model.Prestataire;
import com.filRouge.model.enums.ValidateStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestataireRepository extends JpaRepository<Prestataire, Long> {
    List<Prestataire> findByValidateStatus(ValidateStatus status);
}

