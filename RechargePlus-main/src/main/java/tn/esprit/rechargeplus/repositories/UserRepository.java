package tn.esprit.rechargeplus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.rechargeplus.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
