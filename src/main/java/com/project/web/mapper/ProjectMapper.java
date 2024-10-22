package com.project.web.mapper;

import com.project.web.dto.ProjectDto;
import com.project.web.models.Project;

public class ProjectMapper {
    public static Project mapToProject(ProjectDto projet) {
        Project projectDto = Project.builder()
                .id(projet.getId())
                .name(projet.getName())
                .manager(projet.getManager())
                .startDate(projet.getStartDate())
                .finalDate(projet.getFinalDate())
                .dateFinalReal(projet.getDateFinalReal())
                .totalBudget(projet.getTotalBudget())
                .description(projet.getDescription())
                .status(projet.getStatus())
                .createdBy(projet.getCreatedBy())
                .build();
        return  projectDto;
    }

    public static ProjectDto mapToProjectDto(Project projet) {
        ProjectDto projectDto = ProjectDto.builder()
                .id(projet.getId())
                .name(projet.getName())
                .manager(projet.getManager())
                .startDate(projet.getStartDate())
                .finalDate(projet.getFinalDate())
                .dateFinalReal(projet.getDateFinalReal())
                .totalBudget(projet.getTotalBudget())
                .description(projet.getDescription())
                .status(projet.getStatus())
                .createdBy(projet.getCreatedBy())
                .build();
        return projectDto;
    }
}
