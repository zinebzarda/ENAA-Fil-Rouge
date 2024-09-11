package com.filRouge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int note;
    private String commentaire;
    private LocalDate dateCreation;

    @ManyToOne
    @JoinColumn(name = "demande_service_id")
    private DemandeService demandeService;

}
