package tn.esprit.rechargeplus.services;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;
import tn.esprit.rechargeplus.entities.Project;
import tn.esprit.rechargeplus.repositories.ProjectRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfService {

    private final ProjectRepository projectRepository;

    public PdfService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public byte[] generateProjectPdf() throws IOException {
        List<Project> projects = projectRepository.findAll();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A3);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
// Charger le logo
            String logoPath = "C:/Users/achre/Desktop/Infini 1/semestre2/PIDEV/Logo/Recharge.jpg";
            PDImageXObject logo = PDImageXObject.createFromFile(logoPath, document);

// Ajouter le logo en haut du document
            contentStream.drawImage(logo, 50, 750, 100, 50); // (x, y, width, height)

            // 🔹 Titre du document
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Liste complète des Projets") / 1000 * 16;
            float centerX = (page.getMediaBox().getWidth() - titleWidth) / 2;
            contentStream.newLineAtOffset(centerX, 750);
            contentStream.showText("Liste complète des Projets");
            contentStream.endText();

            // 🔹 Ligne sous le titre pour séparer visuellement
            contentStream.moveTo(50, 740);
            contentStream.lineTo(page.getMediaBox().getWidth() - 50, 740);
            contentStream.stroke();

            // 🔹 Définition des colonnes
            float margin = 50;
            float yStart = 700;
            float yPosition = yStart;
            float rowHeight = 20;
            float[] columnWidths = {120, 160, 100, 100, 100, 100,100};

            // 🔹 En-têtes des colonnes
            String[] headers = {
                     "Titre", "Description", "Secteur", "Lieu", "Montant Requis",
                    "Montant Collecté", "Statut"
            };

            // 🔹 Dessiner l'en-tête du tableau
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
            yPosition -= rowHeight;
            drawRow(contentStream, margin, yPosition, columnWidths, headers);
            contentStream.setFont(PDType1Font.HELVETICA, 8);

            // 🔹 Remplir le tableau avec les projets
            for (Project project : projects) {
                yPosition -= rowHeight;

                // Vérifier si une nouvelle page est nécessaire
                if (yPosition < 50) { // Ajuste cette valeur si nécessaire
                    contentStream.close(); // Fermer le flux courant avant d'en ouvrir un autre
                    page = new PDPage(PDRectangle.A3);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = 700; // Remet à une position plus haute

                    // 🔹 Redessiner l'en-tête sur la nouvelle page
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
                    drawRow(contentStream, margin, yPosition, columnWidths, headers);
                    contentStream.setFont(PDType1Font.HELVETICA, 8);
                    yPosition -= rowHeight;
                }

                String[] row = {
                        project.getTitle() != null ? project.getTitle() : "Non défini",
                        project.getDescription() != null ? project.getDescription() : "Non défini",
                        project.getSector() != null ? project.getSector() : "Non défini",
                        project.getLocation() != null ? project.getLocation() : "Non défini",
                        String.valueOf(project.getAmountRequested()),
                        String.valueOf(project.getAmountCollected()),
                        (project.getStatus() != null) ? project.getStatus().name() : "PENDING"
                };

                drawRow(contentStream, margin, yPosition, columnWidths, row);
            }

            contentStream.close();

            // 🔹 Sauvegarde du PDF dans un flux
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            document.close();
            return outputStream.toByteArray();
        }
    }

    private void drawRow(PDPageContentStream contentStream, float margin, float y, float[] widths, String[] texts) throws IOException {
        float x = margin;
        for (int i = 0; i < texts.length; i++) {
            contentStream.beginText();
            contentStream.newLineAtOffset(x + 5, y + 5);
            contentStream.showText(texts[i] != null ? texts[i] : ""); // Éviter les nulls
            contentStream.endText();
            x += widths[i];
        }
    }
}
