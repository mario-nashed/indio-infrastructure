package com.indio.infrastructure;

import com.indio.infrastructure.model.Project;
import com.indio.infrastructure.repository.ProjectRepository;
import com.indio.infrastructure.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndioApplicationTests {

    @Autowired
    private ProjectService service;

    @Autowired
    private ProjectRepository repository;

    @Test
    void contextLoads() {
        assertNotNull(service);
    }

    @Test
    void shouldSaveAndRetrieveProject() {
        Project p = new Project(
            "Test Projet",
            Project.ProjectType.INFRASTRUCTURE,
            "Paris",
            "Projet de test",
            Project.ProjectStatus.PLANIFIE,
            LocalDate.now(),
            LocalDate.now().plusYears(1),
            100.0
        );
        Project saved = service.save(p);
        assertNotNull(saved.getId());
        assertEquals("Test Projet", saved.getName());
    }

    @Test
    void shouldFindByStatus() {
        service.save(new Project(
            "Projet EN_COURS",
            Project.ProjectType.ENERGIE,
            "Lyon",
            "Description",
            Project.ProjectStatus.EN_COURS,
            LocalDate.now(), LocalDate.now().plusYears(2), 500.0
        ));
        List<Project> results = service.findByStatus(Project.ProjectStatus.EN_COURS);
        assertFalse(results.isEmpty());
    }

    @Test
    void shouldCountProjects() {
        long before = service.countAll();
        service.save(new Project(
            "Comptage Test",
            Project.ProjectType.TRANSPORT,
            "Marseille",
            "Test comptage",
            Project.ProjectStatus.PLANIFIE,
            LocalDate.now(), LocalDate.now().plusYears(1), 200.0
        ));
        assertEquals(before + 1, service.countAll());
    }

    @Test
    void shouldDeleteProject() {
        Project p = service.save(new Project(
            "A Supprimer",
            Project.ProjectType.AMENAGEMENT_URBAIN,
            "Bordeaux",
            "Test suppression",
            Project.ProjectStatus.SUSPENDU,
            LocalDate.now(), LocalDate.now().plusYears(1), 50.0
        ));
        Long id = p.getId();
        service.deleteById(id);
        assertFalse(service.findById(id).isPresent());
    }
}
