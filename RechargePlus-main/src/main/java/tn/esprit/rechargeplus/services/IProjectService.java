package tn.esprit.rechargeplus.services;

import tn.esprit.rechargeplus.entities.Project;
import tn.esprit.rechargeplus.entities.Project_Status;

import java.util.List;

public interface IProjectService {
    Project addProject(Project project);
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    Project updateProject(Project project);
    Project updateProject(Long idProject, Project project);
    void deleteProject(Long id);


    List<Project> searchByTitle(String title);
    List<Project> searchBySector(String sector);
    List<Project> searchByLocation(String location);
    List<Project> filterByStatus(Project_Status status);
}
