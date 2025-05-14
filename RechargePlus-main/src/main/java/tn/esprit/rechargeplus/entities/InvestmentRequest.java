package tn.esprit.rechargeplus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import tn.esprit.rechargeplus.entities.Project;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class InvestmentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idInvestmentRequest;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("expectedROI")
    private Double expectedROI;

    @JsonProperty("investedAmount")
    private Double investedAmount;

    @JsonProperty("actualROI")
    private Double actualROI;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date")
    @JsonProperty("request_date")
    private Date requestDate;

    @JsonProperty("riskScore")
    private Double riskScore;

    @JsonProperty("investmentDuration")
    private int investmentDuration;

    @Enumerated(EnumType.STRING)
    @JsonProperty("status")
    private Investment_Request_Status status;

    @ManyToOne
    @JoinColumn(name = "project_id_project", nullable = false)
    private Project project;


    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "investment_request")
    @JsonIgnore
    private List<Transaction> transactions;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getIdInvestmentRequest() {
        return idInvestmentRequest;
    }

    public void setIdInvestmentRequest(long idInvestmentRequest) {
        this.idInvestmentRequest = idInvestmentRequest;
    }
}
