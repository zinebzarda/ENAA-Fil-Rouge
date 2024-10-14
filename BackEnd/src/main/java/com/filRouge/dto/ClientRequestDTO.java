package com.filRouge.dto;

import com.filRouge.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {
    private String username;
    private String email;
    private String password;
    private String adresse;
    private String tel;

    private String role;
}
