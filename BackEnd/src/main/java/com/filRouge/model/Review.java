package com.filRouge.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="reviews")
@Builder
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_text")
    private String contentText;

    @Column(name = "rating_value")
    private Double ratingValue;

    @ManyToOne()
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne()
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Services services;




    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
}
