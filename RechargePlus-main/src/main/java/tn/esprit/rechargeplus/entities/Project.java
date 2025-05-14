package tn.esprit.rechargeplus.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProject;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("sector")
    private String sector;

    @JsonProperty("location")
    private String location;

    @JsonProperty("amountRequested")
    private double amountRequested;

    @JsonProperty("amountCollected")
    private double amountCollected;

    @JsonProperty("riskCategory")
    private String riskCategory;

    @JsonProperty("investmentAmount")
    @Column(name = "investment_amount")
    private double investmentAmount;

    @JsonProperty("investmentTrends")
    @Column(name = "investment_trends")
    private String investmentTrends;

    @Convert(converter = ProjectStatusConverter.class)
    @Column(nullable = false)
    private Project_Status status = Project_Status.PENDING;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<InvestmentRequest> investmentRequests;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(double amountCollected) {
        this.amountCollected = amountCollected;
    }

    public double getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(double amountRequested) {
        this.amountRequested = amountRequested;
    }

    public Project_Status getStatus() {
        return status;
    }

    public void setStatus(Project_Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(String riskCategory) {
        this.riskCategory = riskCategory;
    }

    public double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(double investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public String getInvestmentTrends() {
        return investmentTrends;
    }

    public void setInvestmentTrends(String investmentTrends) {
        this.investmentTrends = investmentTrends;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<InvestmentRequest> getInvestmentRequests() {
        return investmentRequests;
    }

    public void setInvestmentRequests(List<InvestmentRequest> investmentRequests) {
        this.investmentRequests = investmentRequests;
    }

}