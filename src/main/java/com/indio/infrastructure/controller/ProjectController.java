package com.indio.infrastructure.controller;

import com.indio.infrastructure.model.Project;
import com.indio.infrastructure.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("projects", service.findAll());
        model.addAttribute("totalProjects", service.countAll());
        model.addAttribute("enCours", service.countByStatus(Project.ProjectStatus.EN_COURS));
        model.addAttribute("termines", service.countByStatus(Project.ProjectStatus.TERMINE));
        model.addAttribute("planifies", service.countByStatus(Project.ProjectStatus.PLANIFIE));
        return "projects/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("types", Project.ProjectType.values());
        model.addAttribute("statuses", Project.ProjectStatus.values());
        return "projects/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Project project, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", Project.ProjectType.values());
            model.addAttribute("statuses", Project.ProjectStatus.values());
            return "projects/form";
        }
        service.save(project);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Project project = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Projet introuvable : " + id));
        model.addAttribute("project", project);
        model.addAttribute("types", Project.ProjectType.values());
        model.addAttribute("statuses", Project.ProjectStatus.values());
        return "projects/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute Project project,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("types", Project.ProjectType.values());
            model.addAttribute("statuses", Project.ProjectStatus.values());
            return "projects/form";
        }
        project.setId(id);
        service.save(project);
        return "redirect:/projects";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/projects";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Project project = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Projet introuvable : " + id));
        model.addAttribute("project", project);
        return "projects/detail";
    }
}
