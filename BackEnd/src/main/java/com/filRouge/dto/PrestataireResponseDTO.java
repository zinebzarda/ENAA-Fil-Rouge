package com.filRouge.dto;



import com.filRouge.model.enums.ValidateStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestataireResponseDTO {
    private String username;
    private String email;
    private String domaineExpertise;
    private String disponibilites;
    private String experience;
    private Integer tel;
    private ValidateStatus status;
}