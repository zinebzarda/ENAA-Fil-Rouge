package com.filRouge.dto;

import com.filRouge.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonneDTO {
    private int id;
    private String username;
    private String email;
    private String password;

}
