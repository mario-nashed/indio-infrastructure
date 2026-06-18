package com.indio.infrastructure.repository;

import com.indio.infrastructure.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStatus(Project.ProjectStatus status);
    List<Project> findByType(Project.ProjectType type);
}
