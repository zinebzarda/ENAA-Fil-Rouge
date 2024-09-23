package com.filRouge.dto;

import com.filRouge.model.enums.ValidateStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestataireRequestDTO {
    private String username;
    private String email;
    private String password;
    private String domaineExpertise;
    private String disponibilites;
    private String experience;
    private String role;
    private ValidateStatus status;
}