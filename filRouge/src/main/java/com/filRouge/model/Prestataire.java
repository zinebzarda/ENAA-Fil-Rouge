package com.filRouge.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prestataire extends Personne {
    
    private String domaineExpertise;
    private String disponibilites;
    private String experience;
}
