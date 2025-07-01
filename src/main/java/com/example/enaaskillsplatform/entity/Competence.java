package com.example.enaaskillsplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "copetences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String titre;
    private String description;
    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SousCompetence> sousCompetences;
}
