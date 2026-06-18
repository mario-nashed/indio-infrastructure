package com.indio.infrastructure.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectType type;

    @NotBlank
    private String location;

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private LocalDate startDate;
    private LocalDate endDate;

    private Double budgetMillions;

    public Project() {}

    public Project(String name, ProjectType type, String location,
                   String description, ProjectStatus status,
                   LocalDate startDate, LocalDate endDate, Double budgetMillions) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetMillions = budgetMillions;
    }

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ProjectType getType() { return type; }
    public void setType(ProjectType type) { this.type = type; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ProjectStatus getStatus() { return status; }
    public void setStatus(ProjectStatus status) { this.status = status; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Double getBudgetMillions() { return budgetMillions; }
    public void setBudgetMillions(Double budgetMillions) { this.budgetMillions = budgetMillions; }

    public enum ProjectType {
        INFRASTRUCTURE, ENERGIE, TRANSPORT, AMENAGEMENT_URBAIN
    }

    public enum ProjectStatus {
        EN_COURS, PLANIFIE, TERMINE, SUSPENDU
    }
}
