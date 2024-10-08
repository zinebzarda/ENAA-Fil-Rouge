package com.filRouge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String adresse;
}
