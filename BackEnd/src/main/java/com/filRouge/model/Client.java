package com.filRouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<DemandeService> demandes;

    @OneToMany(mappedBy = "client")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

}
