package com.filRouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filRouge.model.enums.ValidateStatus;
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

    @Enumerated(EnumType.STRING)
    private ValidateStatus validateStatus = ValidateStatus.EN_ATTENTE;

    @OneToMany(mappedBy = "prestataire")
    @JsonIgnore
    private List<Services> services;
}
