package com.project.web.controller;

import com.project.web.dto.ProjectDto;
import com.project.web.models.Project;
import com.project.web.models.UserEntity;
import com.project.web.security.SecurityUtil;
import com.project.web.service.ProjectService;
import com.project.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String listProjects(Model model) {
        UserEntity user = new UserEntity();
        List<ProjectDto> projects = projectService.findAllProjects();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByEmail(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("projects", projects);
        return "projects-list";
    }

    @GetMapping("/projects/{projectId}")
    public String projectDetail(@PathVariable("projectId") long projectId, Model model) {
        UserEntity user = new UserEntity();
        ProjectDto projectDto = projectService.findProjectById(projectId);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByEmail(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("project", projectDto);
        return "projects-detail";
    }

    @GetMapping("/projects/new")
    public String createProjectForm(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "projects-create";
    }

    @GetMapping("/projects/{projectId}/delete")
    public String deleteProject(@PathVariable("projectId")Long projectId) {
        projectService.delete(projectId);
        return "redirect:/projects";
    }

    @GetMapping("/projects/search")
    public String searchProject(@RequestParam(value = "query") String query, Model model) {
        List<ProjectDto> projects = projectService.searchProjects(query);
        model.addAttribute("projects", projects);
        return "projects-list";
    }

    @PostMapping("/projects/new")
    public String saveProject(@Valid @ModelAttribute("project") ProjectDto projectDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("project", projectDto);
            return "projects-create";
        }
        projectService.saveProject(projectDto);
        return "redirect:/projects";
    }

    @GetMapping("/projects/{projectId}/edit")
    public String editProjectForm(@PathVariable("projectId") Long projectId, Model model) {
        ProjectDto project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "projects-edit";
    }
    @PostMapping("/projects/{projectId}/edit")
    public String updateProject(@PathVariable("projectId") Long projectId,
                             @Valid @ModelAttribute("project") ProjectDto project,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("project", project);
            return "projects-edit";
        }
        project.setId(projectId);
        projectService.updateProject(project);
        return "redirect:/projects";
    }
}
