package com.filRouge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DemandeServiceDTO
{
    private int id;
    private String description;
    private int prestataireId;
    private int serviceId;
}
