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
public class Client extends Personne {
    private String adresse;


    @OneToMany(mappedBy = "client")
    private List<DemandeService> demandes;

    @OneToMany(mappedBy = "client")
    private List<Contact> contacts;

}
