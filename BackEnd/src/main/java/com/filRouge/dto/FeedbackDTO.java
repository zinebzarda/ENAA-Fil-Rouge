package com.filRouge.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    private int note;
    private String commentaire;
    private LocalDate dateCreation;
}
