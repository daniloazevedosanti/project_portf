package com.project.web.repository;

import com.project.web.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String url);

    @Query("SELECT c from Project c WHERE c.name LIKE CONCAT('%', :query, '%')")
    List<Project> searchProjects(String query);
}
