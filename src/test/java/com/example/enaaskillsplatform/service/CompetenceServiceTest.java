package com.example.enaaskillsplatform.service;

import com.example.enaaskillsplatform.dto.CompetenceDTO;
import com.example.enaaskillsplatform.entity.Competence;
import com.example.enaaskillsplatform.repository.CompetenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Active l'utilisation de Mockito
class CompetenceServiceTest {

    @Mock // Crée un mock (une fausse version) du Repository
    private CompetenceRepository competenceRepository;

    @InjectMocks // Crée une instance de CompetenceService et y injecte les mocks déclarés avec @Mock
    private CompetenceService competenceService;

    private Competence competence;
    private CompetenceDTO competenceDTO;

    @BeforeEach
    void setUp() {
        // Initialiser des objets de test réutilisables
        competence = new Competence();
        competence.setId(1L);
        competence.setCode("C1");
        competence.setTitre("Test Titre");

        competenceDTO = new CompetenceDTO();
        competenceDTO.setCode("C1");
        competenceDTO.setTitre("Test Titre");
        competenceDTO.setDescription("Test Desc");
    }

    @Test
    void mmwhenGetCompetenceById_withExistingId_shouldReturnCompetence() {
        // Arrange (Préparation) : On dit au mock quoi faire
        when(competenceRepository.findById(1L)).thenReturn(Optional.of(competence));

        // Act (Action) : On appelle la méthode à tester
        Competence found = competenceService.getCompetenceById(1L);

        // Assert (Vérification) : On vérifie le résultat
        assertThat(found).isNotNull();
        assertThat(found.getTitre()).isEqualTo("Test Titre");
    }

    @Test
    void whenGetCompetenceById_withNonExistingId_shouldThrowException() {
        // Arrange
        when(competenceRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> {
            competenceService.getCompetenceById(99L);
        });
    }

    @Test
    void whenCreateCompetence_shouldReturnSavedCompetence() {
        // Arrange
        // On simule l'action de sauvegarde du repository
        when(competenceRepository.save(any(Competence.class))).thenReturn(competence);

        // Act
        Competence created = competenceService.createCompetence(competenceDTO);

        // Assert
        assertThat(created).isNotNull();
        assertThat(created.getCode()).isEqualTo("C1");
    }
}