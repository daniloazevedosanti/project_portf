package com.project.web.service;

import com.project.web.dto.ProjectDto;
import com.project.web.models.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> findAllProjects();
    Project saveProject(ProjectDto ProjectDto);
    ProjectDto findProjectById(Long ProjectId);
    void updateProject(ProjectDto Project);
    void delete(Long ProjectId);
    List<ProjectDto> searchProjects(String query);
}
