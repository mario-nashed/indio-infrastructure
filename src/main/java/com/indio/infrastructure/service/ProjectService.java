package com.indio.infrastructure.service;

import com.indio.infrastructure.model.Project;
import com.indio.infrastructure.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> findAll() {
        return repository.findAll();
    }

    public Optional<Project> findById(Long id) {
        return repository.findById(id);
    }

    public Project save(Project project) {
        return repository.save(project);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Project> findByStatus(Project.ProjectStatus status) {
        return repository.findByStatus(status);
    }

    public List<Project> findByType(Project.ProjectType type) {
        return repository.findByType(type);
    }

    public long countAll() {
        return repository.count();
    }

    public long countByStatus(Project.ProjectStatus status) {
        return repository.findByStatus(status).size();
    }
}
