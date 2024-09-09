package com.filRouge.repository;

import com.filRouge.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
//    List<Client> findByAdress(String adress);
}