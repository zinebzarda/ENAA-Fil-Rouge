package com.filRouge.repository;

import com.filRouge.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
