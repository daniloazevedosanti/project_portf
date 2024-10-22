package com.project.web.service.impl;

import com.project.web.dto.ProjectDto;
import com.project.web.models.Project;
import com.project.web.models.UserEntity;
import com.project.web.repository.ProjectRepository;
import com.project.web.repository.UserRepository;
import com.project.web.security.SecurityUtil;
import com.project.web.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.web.mapper.ProjectMapper.mapToProject;
import static com.project.web.mapper.ProjectMapper.mapToProjectDto;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDto> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map((project) -> mapToProjectDto(project)).collect(Collectors.toList());
    }

    @Override
    public Project saveProject(ProjectDto projectDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(username);
        Project project = mapToProject(projectDto);
        project.setCreatedBy(user);
        return projectRepository.save(project);
    }

    @Override
    public ProjectDto findProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        return mapToProjectDto(project);
    }

    @Override
    public void updateProject(ProjectDto projectDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Project project = mapToProject(projectDto);
        //project.setCreatedBy(user);
        projectRepository.save(project);
    }

    @Override
    public void delete(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<ProjectDto> searchProjects(String query) {
        List<Project> projects = projectRepository.searchProjects(query);
        return projects.stream().map(project -> mapToProjectDto(project)).collect(Collectors.toList());
    }
}
