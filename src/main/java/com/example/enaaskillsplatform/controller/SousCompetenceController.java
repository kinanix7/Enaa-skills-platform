package com.example.enaaskillsplatform.controller;

import com.example.enaaskillsplatform.dto.SousCompetenceDTO;
import com.example.enaaskillsplatform.entity.SousCompetence;
import com.example.enaaskillsplatform.service.SousCompetenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sous-competences")
@RequiredArgsConstructor
public class SousCompetenceController {

    private final SousCompetenceService sousCompetenceService;

    @PostMapping
    public SousCompetence createSousCompetence(@RequestBody SousCompetenceDTO sousCompetenceDTO) {
        return sousCompetenceService.createSousCompetence(sousCompetenceDTO.getCompetenceId(), sousCompetenceDTO);
    }

    @GetMapping("/{id}")
    public SousCompetence getSousCompetenceById(@PathVariable Long id) {
        return sousCompetenceService.getSousCompetenceById(id);
    }

    @PutMapping("/{id}")
    public SousCompetence updateSousCompetence(@PathVariable Long id, @RequestBody SousCompetenceDTO sousCompetenceDTO) {
        return sousCompetenceService.updateSousCompetence(id, sousCompetenceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSousCompetence(@PathVariable Long id) {
        sousCompetenceService.deleteSousCompetence(id);
    }
}