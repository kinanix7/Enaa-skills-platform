package com.example.enaaskillsplatform.service;

import com.example.enaaskillsplatform.dto.SousCompetenceDTO;
import com.example.enaaskillsplatform.entity.Competence;
import com.example.enaaskillsplatform.entity.SousCompetence;
import com.example.enaaskillsplatform.repository.SousCompetenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SousCompetenceService {

    private final SousCompetenceRepository sousCompetenceRepository;
    private final CompetenceService competenceService;

    public SousCompetence getSousCompetenceById(Long id) {
        return sousCompetenceRepository.findById(id)
                .orElse(null);
    }

    public SousCompetence createSousCompetence(Long competenceId, SousCompetenceDTO sousCompetenceDTO) {
        Competence competence = competenceService.getCompetenceById(competenceId);
        SousCompetence newSousCompetence = new SousCompetence();
        newSousCompetence.setTitre(sousCompetenceDTO.getTitre());
        newSousCompetence.setCompetence(competence);
        return sousCompetenceRepository.save(newSousCompetence);
    }

    public SousCompetence updateSousCompetence(Long id, SousCompetenceDTO sousCompetenceDTO) {
        SousCompetence existingSousCompetence = getSousCompetenceById(id);
        existingSousCompetence.setTitre(sousCompetenceDTO.getTitre());
        return sousCompetenceRepository.save(existingSousCompetence);
    }

    public void deleteSousCompetence(Long id) {
        SousCompetence sousCompetence = getSousCompetenceById(id);
        sousCompetenceRepository.delete(sousCompetence);
    }
}