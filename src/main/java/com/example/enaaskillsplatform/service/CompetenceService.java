package com.example.enaaskillsplatform.service;

import com.example.enaaskillsplatform.dto.CompetenceDTO;
import com.example.enaaskillsplatform.entity.Competence;
import com.example.enaaskillsplatform.repository.CompetenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetenceService {

    private final CompetenceRepository competenceRepository;

    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    public Competence getCompetenceById(Long id) {
        return competenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Compétence non trouvée avec l'id : " + id
                ));
    }
    public Competence createCompetence(CompetenceDTO competenceDTO) {
        Competence newCompetence = new Competence();
        newCompetence.setCode(competenceDTO.getCode());
        newCompetence.setTitre(competenceDTO.getTitre());
        newCompetence.setDescription(competenceDTO.getDescription());
        return competenceRepository.save(newCompetence);
    }

    public Competence updateCompetence(Long id, CompetenceDTO competenceDTO) {
        Competence existingCompetence = getCompetenceById(id);
        existingCompetence.setCode(competenceDTO.getCode());
        existingCompetence.setTitre(competenceDTO.getTitre());
        existingCompetence.setDescription(competenceDTO.getDescription());
        return competenceRepository.save(existingCompetence);
    }

    public void deleteCompetence(Long id) {
        Competence competence = getCompetenceById(id);
        competenceRepository.delete(competence);
    }
}