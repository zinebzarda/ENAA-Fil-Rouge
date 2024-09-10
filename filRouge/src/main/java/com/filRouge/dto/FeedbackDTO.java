package com.filRouge.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    private int note;
    private String commentaire;
    private Date dateCreation;
}
