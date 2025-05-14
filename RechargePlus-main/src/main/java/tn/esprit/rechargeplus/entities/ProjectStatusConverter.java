package tn.esprit.rechargeplus.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProjectStatusConverter implements AttributeConverter<Project_Status, String> {

    @Override
    public String convertToDatabaseColumn(Project_Status status) {
        return status == null ? null : status.name();
    }

    @Override
    public Project_Status convertToEntityAttribute(String dbValue) {
        try {
            return dbValue == null ? null : Project_Status.valueOf(dbValue.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            // Optionnel : tu peux logger l'erreur ici
            System.err.println("⚠️ Valeur inconnue de statut projet : " + dbValue + ", valeur par défaut utilisée.");
            return Project_Status.PENDING; // valeur par défaut
        }
    }
}
