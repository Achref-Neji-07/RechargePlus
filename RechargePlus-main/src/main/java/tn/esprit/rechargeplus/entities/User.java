package tn.esprit.rechargeplus.entities;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private role_enum role;
    private String address;
    private String mobile_number;
    private Date birth_date;
    private Date registered_at;
    private String country;
    private String status; // active, inactive, banned
    @Lob
    private byte[] face_photo;
    @Lob
    private byte[] national_identity_card;
    @OneToMany(mappedBy = "user")
    private List<Product> Produits;

    @OneToMany (mappedBy = "user")
    private List<Account> accounts;
    @OneToMany (mappedBy = "user")
    private List<Project> projects;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
