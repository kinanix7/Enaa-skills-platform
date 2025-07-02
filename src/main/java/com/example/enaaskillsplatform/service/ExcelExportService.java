package com.example.enaaskillsplatform.service;

import com.example.enaaskillsplatform.entity.Competence;
import com.example.enaaskillsplatform.entity.SousCompetence;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExcelExportService {

    public void exportCompetencesToExcel(List<Competence> competences, OutputStream outputStream) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Rapport de Compétences");

            // Créer le style pour les en-têtes
            CellStyle headerStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(14);
            headerStyle.setFont(font);

            // Créer la ligne d'en-tête
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID Compétence", "Code", "Titre Compétence", "ID Sous-Compétence", "Titre Sous-Compétence"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Remplir les données
            int rowNum = 1;
            for (Competence competence : competences) {
                for (SousCompetence sousCompetence : competence.getSousCompetences()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(competence.getId());
                    row.createCell(1).setCellValue(competence.getCode());
                    row.createCell(2).setCellValue(competence.getTitre());
                    row.createCell(3).setCellValue(sousCompetence.getId());
                    row.createCell(4).setCellValue(sousCompetence.getTitre());
                }
            }

            // Écrire le contenu dans le flux de sortie
            workbook.write(outputStream);
        }
    }
}