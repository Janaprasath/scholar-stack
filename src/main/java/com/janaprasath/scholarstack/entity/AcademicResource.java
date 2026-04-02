package com.janaprasath.scholarstack.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "resources")
@Data
public class AcademicResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "subject_code")
    private String subjectCode;

    @JsonProperty("url")
    private String fileUrl;
    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private User uploader;

    private LocalDateTime uploadDate = LocalDateTime.now();
}

