package com.example.enaaskillsplatform.controller;

import com.example.enaaskillsplatform.dto.CompetenceDTO;
import com.example.enaaskillsplatform.entity.Competence;
import com.example.enaaskillsplatform.service.CompetenceService;
import com.example.enaaskillsplatform.service.ExcelExportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/competences")
@RequiredArgsConstructor
public class CompetenceController {

    private final CompetenceService competenceService;
    private final ExcelExportService excelExportService;


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

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        // 1. Définir le type de contenu de la réponse
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // 2. Créer un nom de fichier dynamique
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=rapport_competences_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        // 3. Récupérer les données à exporter
        List<Competence> competences = competenceService.getAllCompetences();

        // 4. Appeler le service d'export
        excelExportService.exportCompetencesToExcel(competences, response.getOutputStream());
    }
}