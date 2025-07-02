package com.example.enaaskillsplatform.controller;

import com.example.enaaskillsplatform.dto.CompetenceDTO;
import com.example.enaaskillsplatform.entity.Competence;
import com.example.enaaskillsplatform.service.CompetenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competences")
@RequiredArgsConstructor
public class CompetenceController {

    private final CompetenceService competenceService;

    @GetMapping
    public List<Competence> getAllCompetences() {
        return competenceService.getAllCompetences();
    }

    @GetMapping("/{id}")
    public Competence getCompetenceById(@PathVariable Long id) {
        return competenceService.getCompetenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Competence createCompetence(@RequestBody CompetenceDTO competenceDTO) {
        return competenceService.createCompetence(competenceDTO);
    }

    @PutMapping("/{id}")
    public Competence updateCompetence(@PathVariable Long id, @RequestBody CompetenceDTO competenceDTO) {
        return competenceService.updateCompetence(id, competenceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCompetence(@PathVariable Long id) {
        competenceService.deleteCompetence(id);
    }
}