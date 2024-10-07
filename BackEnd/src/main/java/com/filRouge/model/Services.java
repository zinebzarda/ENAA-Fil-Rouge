package com.filRouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Float prix;
    private String image;

    @ManyToOne
    @JoinColumn(name = "prestataire_id")
    private Prestataire prestataire;

    @OneToMany(mappedBy = "service")
    private List<DemandeService> demandes;

    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

}
