package com.filRouge.repository;

import com.filRouge.model.DemandeService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeServiceRepository extends JpaRepository<DemandeService, Long> {

    List<DemandeService> findDemandeServiceByServiceId(Long id);
}
