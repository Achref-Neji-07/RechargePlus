package tn.esprit.rechargeplus.controllers;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.rechargeplus.entities.InvestmentRequest;
import tn.esprit.rechargeplus.services.IInvestmentRequestService;

import java.util.List;
@Slf4j // si tu utilises lombok
@RestController
@RequestMapping("/api/investmentRequest") // 🔥 ajoute ceci


//@RequestMapping("/investmentRequest")
@AllArgsConstructor
public class InvestmentRequestController {
    @Autowired
    private IInvestmentRequestService investmentRequestService;

    @PostConstruct
    public void init() {
        System.out.println("✅ InvestmentRequestController loaded!");
    }

    @PostMapping("/add")
    public ResponseEntity<InvestmentRequest> addInvestmentRequest(@RequestBody InvestmentRequest investmentRequest) {
        return ResponseEntity.ok(investmentRequestService.addInvestmentRequest(investmentRequest));
    }

    @GetMapping("/getAll")
    public List<InvestmentRequest> getAllInvestmentRequests() {
        return investmentRequestService.getAllInvestmentRequests();
    }

    @GetMapping("/get/{id}")
    public InvestmentRequest getInvestmentRequestById(@PathVariable Long id) {
        return investmentRequestService.getInvestmentRequestById(id);
    }

    @PutMapping("/update")
    public InvestmentRequest updateInvestmentRequest(@RequestBody InvestmentRequest investmentRequest) {
        return investmentRequestService.updateInvestmentRequest(investmentRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInvestmentRequest(@PathVariable Long id) {
        investmentRequestService.deleteInvestmentRequest(id);
    }
}
