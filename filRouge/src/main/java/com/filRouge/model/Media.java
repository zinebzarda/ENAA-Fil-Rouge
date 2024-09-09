package com.filRouge.model;

import com.filRouge.model.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mediaUrl;
    private String mediaId;
    private Type type;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;
}