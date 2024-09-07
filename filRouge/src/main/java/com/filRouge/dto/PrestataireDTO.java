package com.filRouge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class PrestataireDTO {
    private String username;
    private String email;
    private String domaineExpertise;
    private String disponibilites;
    private String experience;

}