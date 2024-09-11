package com.filRouge.model;

import com.filRouge.model.enums.StatutService;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
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

    @Enumerated(EnumType.STRING)
    private StatutService statut;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;

    @OneToMany(mappedBy = "demandeService")
    private List<Feedback> feedbacks;

}
