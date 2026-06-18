package com.indio.infrastructure;

import com.indio.infrastructure.model.Project;
import com.indio.infrastructure.service.ProjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProjectService service;

    public DataInitializer(ProjectService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        if (service.countAll() == 0) {
            service.save(new Project(
                "Ligne à Grande Vitesse Paris-Lyon 2",
                Project.ProjectType.TRANSPORT,
                "Paris – Lyon, France",
                "Extension de la ligne TGV Paris-Lyon avec modernisation des infrastructures ferroviaires sur 450 km.",
                Project.ProjectStatus.EN_COURS,
                LocalDate.of(2023, 3, 1),
                LocalDate.of(2027, 12, 31),
                2800.0
            ));
            service.save(new Project(
                "Parc Éolien Offshore Bretagne",
                Project.ProjectType.ENERGIE,
                "Côte bretonne, France",
                "Installation de 45 éoliennes offshore pour une capacité totale de 630 MW dans le cadre de la transition énergétique.",
                Project.ProjectStatus.EN_COURS,
                LocalDate.of(2024, 1, 15),
                LocalDate.of(2026, 6, 30),
                1200.0
            ));
            service.save(new Project(
                "Pont de la Méditerranée",
                Project.ProjectType.INFRASTRUCTURE,
                "Marseille, France",
                "Construction d'un pont suspendu de 1,2 km reliant le port de Marseille à la zone industrielle sud.",
                Project.ProjectStatus.PLANIFIE,
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2029, 3, 31),
                650.0
            ));
            service.save(new Project(
                "Écoquartier La Défense 2030",
                Project.ProjectType.AMENAGEMENT_URBAIN,
                "La Défense, France",
                "Réaménagement de 15 hectares du quartier d'affaires de La Défense avec intégration d'espaces verts et de mobilités douces.",
                Project.ProjectStatus.PLANIFIE,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2030, 12, 31),
                950.0
            ));
            service.save(new Project(
                "Autoroute A89 Extension",
                Project.ProjectType.TRANSPORT,
                "Bordeaux – Toulouse, France",
                "Extension de l'autoroute A89 sur 120 km entre Bordeaux et Toulouse avec 3 échangeurs.",
                Project.ProjectStatus.TERMINE,
                LocalDate.of(2020, 6, 1),
                LocalDate.of(2024, 11, 30),
                1100.0
            ));
            service.save(new Project(
                "Centrale Solaire Sahel",
                Project.ProjectType.ENERGIE,
                "Ouagadougou, Burkina Faso",
                "Construction d'une centrale solaire photovoltaïque de 200 MW pour alimenter 500 000 foyers.",
                Project.ProjectStatus.EN_COURS,
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2026, 3, 31),
                320.0
            ));
        }
    }
}
