package tn.esprit.rechargeplus.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.rechargeplus.entities.Project;
import tn.esprit.rechargeplus.entities.Project_Status;
import tn.esprit.rechargeplus.services.IProjectService;
import tn.esprit.rechargeplus.services.PdfService;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.io.File;
import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final IProjectService projectService;
    private final PdfService pdfService;

    @Autowired
    public ProjectController(IProjectService projectService, PdfService pdfService) {
        this.projectService = projectService;
        this.pdfService = pdfService;
    }

    @GetMapping("/downloadPdf")
    public ResponseEntity<byte[]> downloadProjectPdf() throws IOException {
        byte[] pdfContent = pdfService.generateProjectPdf();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=projects.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfContent);
    }

    // 🔹 Recherche par titre avec @RequestParam (PAS @PathVariable)
    @GetMapping("/search/title")
    public List<Project> searchByTitle(@RequestParam String title) {
        System.out.println("✅ Requête reçue pour chercher un projet avec le titre: " + title);
        return projectService.searchByTitle(title);
    }

    // 🔹 Recherche par secteur
    @GetMapping("/search/sector/{sector}")
    public List<Project> searchBySector(@PathVariable("sector") String sector) {
        return projectService.searchBySector(sector);
    }

    // 🔹 Recherche par localisation
    @GetMapping("/search/location/{location}")
    public List<Project> searchByLocation(@PathVariable("location") String location) {
        return projectService.searchByLocation(location);
    }

    @GetMapping("/filterByStatus/{status}")
    public List<Project> filterByStatus(@PathVariable("status") Project_Status status) {
        return projectService.filterByStatus(status);
    }

    @GetMapping("/getProjects")
    public List<Project> allProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/get/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("/addProject")
    public Project addProject(@RequestBody Project project) {
        System.out.println("Projet reçu : " + project);
        return projectService.addProject(project);
    }
    @PutMapping("/updateProject/{idProject}")
    public Project updateProject(@PathVariable Long idProject, @RequestBody Project project) {
        return projectService.updateProject(idProject, project);
    }




    @PutMapping("/updateProject")
    public Project updateProject(@RequestBody Project project) {
        return projectService.updateProject(project);
    }
    @DeleteMapping("/deleteProject/{id}")
    public void deleteProject(@PathVariable Long id) {
        System.out.println("🗑️ Suppression du projet ID : " + id);
        projectService.deleteProject(id);
    }


    @GetMapping("/statuses")
    public Project_Status[] getAllStatuses() {
        return Project_Status.values();
    }


}
