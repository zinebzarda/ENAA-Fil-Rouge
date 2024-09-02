package com.filRouge.repository;

import com.filRouge.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Services,Long> {
    List<Services> findByTitreContainingOrDescriptionContaining(String titre, String description);
    List<Services> findByTitreContaining(String titre);

}
