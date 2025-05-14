package tn.esprit.rechargeplus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rechargeplus.entities.Project;
import tn.esprit.rechargeplus.entities.Project_Status;
import tn.esprit.rechargeplus.repositories.ProjectRepository;
import tn.esprit.rechargeplus.entities.Project;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    @Override
    public Project addProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        Optional<Project> optionalRequest = projectRepo.findById(id);
        return optionalRequest.orElse(null);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    @Override
    public List<Project> searchByTitle(String title) {
        return projectRepo.findByTitleContainingIgnoreCase(title);
    }

    public List<Project> searchBySector(String sector) {
        return projectRepo.findBySectorIgnoreCase(sector);
    }

    public List<Project> searchByLocation(String location) {
        return projectRepo.findByLocationContainingIgnoreCase(location);
    }
    @Override
    public Project updateProject(Long idProject, Project updatedProject) {
        Project existing = projectRepo.findById(idProject)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + idProject));
        // mise à jour des champs
        existing.setTitle(updatedProject.getTitle());
        existing.setDescription(updatedProject.getDescription());
        existing.setSector(updatedProject.getSector());
        existing.setLocation(updatedProject.getLocation());
        existing.setAmountRequested(updatedProject.getAmountRequested());
        existing.setAmountCollected(updatedProject.getAmountCollected());
        existing.setRiskCategory(updatedProject.getRiskCategory());
        existing.setInvestmentAmount(updatedProject.getInvestmentAmount());
        existing.setInvestmentTrends(updatedProject.getInvestmentTrends());
        existing.setStatus(updatedProject.getStatus());
        return projectRepo.save(existing);
    }




    @Override
    public List<Project> filterByStatus(Project_Status status) {
        return projectRepo.findByStatus(status);
    }
}