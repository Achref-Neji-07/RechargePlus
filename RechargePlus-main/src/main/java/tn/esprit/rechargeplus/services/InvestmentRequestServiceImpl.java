package tn.esprit.rechargeplus.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.rechargeplus.entities.InvestmentRequest;
import tn.esprit.rechargeplus.entities.Investment_Request_Status;
import tn.esprit.rechargeplus.entities.Project;
import tn.esprit.rechargeplus.entities.User;
import tn.esprit.rechargeplus.repositories.InvestmentRequestRepository;
import tn.esprit.rechargeplus.repositories.ProjectRepository;
import tn.esprit.rechargeplus.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentRequestServiceImpl implements IInvestmentRequestService {

    @Autowired
    private InvestmentRequestRepository investmentRequestRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public InvestmentRequest addInvestmentRequest(InvestmentRequest req) {
        // ⚠️ Vérifie la présence du projet
        if (req.getProject() == null || req.getProject().getIdProject() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Projet non fourni");
        }

        // ✅ Recharge le projet depuis la base (obligatoire)
        Project project = projectRepository.findById(req.getProject().getIdProject())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projet non trouvé"));
        req.setProject(project);

        // (optionnel si tu veux lier un utilisateur plus tard)
    /*
    if (req.getUser() != null && req.getUser().getIdUser() != null) {
        User u = userRepository.findById(req.getUser().getIdUser())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
        req.setUser(u);
    }
    */

        // Auto date (sécurité)
        req.setRequestDate(new Date());

        return investmentRequestRepository.save(req);
    }






    @Override
    public List<InvestmentRequest> getAllInvestmentRequests() {
        return investmentRequestRepository.findAll();
    }

    @Override
    public InvestmentRequest getInvestmentRequestById(Long id) {
        return investmentRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Requête non trouvée."));
    }

    @Override
    public InvestmentRequest updateInvestmentRequest(InvestmentRequest investmentRequest) {
        if (investmentRequest.getProject() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID requis pour la mise à jour.");
        }
        return investmentRequestRepository.save(investmentRequest);
    }

    @Override
    public void deleteInvestmentRequest(Long id) {
        if (!investmentRequestRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requête à supprimer non trouvée.");
        }
        investmentRequestRepository.deleteById(id);
    }
}
