package tn.esprit.rechargeplus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.rechargeplus.entities.Project;
import tn.esprit.rechargeplus.entities.Project_Status;
import tn.esprit.rechargeplus.repositories.InvestmentRequestRepository;
import tn.esprit.rechargeplus.repositories.ProjectRepository;
import tn.esprit.rechargeplus.services.IInvestmentRequestService;
import java.util.Optional;

import java.util.List;
@Repository

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByTitleContainingIgnoreCase(String title);
    List<Project> findBySectorIgnoreCase(String sector);
    List<Project> findByLocationContainingIgnoreCase(String location);
    List<Project> findByStatus(Project_Status status);
    @Query("SELECT p FROM Project p ORDER BY p.amountCollected DESC LIMIT 5")
    List<Project> findTopProjectsByInvestment();

}
