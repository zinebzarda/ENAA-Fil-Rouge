package com.filRouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filRouge.model.enums.ValidateStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "demande_service")

public class DemandeService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_demmande")
    private LocalDate dateDemmande;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "serviceId")
    private Services service;


}
