package tn.esprit.rechargeplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "tn.esprit.rechargeplus")
public class RechargePlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(RechargePlusApplication.class, args);
    }

}
