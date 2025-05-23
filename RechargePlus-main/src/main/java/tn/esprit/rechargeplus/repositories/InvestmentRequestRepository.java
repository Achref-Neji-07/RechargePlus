package tn.esprit.rechargeplus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.rechargeplus.entities.InvestmentRequest;
import tn.esprit.rechargeplus.repositories.InvestmentRequestRepository;
import tn.esprit.rechargeplus.repositories.ProjectRepository;
import tn.esprit.rechargeplus.services.IInvestmentRequestService;
import java.util.Optional;

import java.util.List;
@Repository

public interface InvestmentRequestRepository extends JpaRepository<InvestmentRequest, Long> {
    // ✅ Récupérer le montant total investi
    @Query("SELECT COALESCE(SUM(i.amount), 0.0) FROM InvestmentRequest i")
    Double getTotalInvestedAmount();

    // ✅ Répartition des investissements par secteur
    @Query("SELECT i.project.sector, SUM(i.amount) FROM InvestmentRequest i GROUP BY i.project.sector")
    List<Object[]> getInvestmentBySector();
    @Query("SELECT ir.project.location, SUM(ir.amount) FROM InvestmentRequest ir GROUP BY ir.project.location")
    List<Object[]> getInvestmentByLocation();

    @Query("SELECT ir.project.riskCategory, SUM(ir.amount) FROM InvestmentRequest ir GROUP BY ir.project.riskCategory")
    List<Object[]> getInvestmentByRiskCategory();

    @Query("SELECT AVG(ir.expectedROI) FROM InvestmentRequest ir")
    Double getAverageROI();
}
