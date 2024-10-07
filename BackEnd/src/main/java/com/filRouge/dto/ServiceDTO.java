package com.filRouge.dto;

import com.filRouge.model.Prestataire;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
public class ServiceDTO {
    private String titre;
    private String description;
    private Float prix;
    private String image;
}
