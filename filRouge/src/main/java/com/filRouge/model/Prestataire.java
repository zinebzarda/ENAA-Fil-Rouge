package com.filRouge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prestataire extends Personne {

    private String domaineExpertise;
    private String disponibilites;
    private String experience;

    @OneToMany(mappedBy = "prestataire")
    private List<Service> services;
}
