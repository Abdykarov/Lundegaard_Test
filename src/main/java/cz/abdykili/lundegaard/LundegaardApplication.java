package cz.abdykili.lundegaard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LundegaardApplication {

    public static void main(String[] args) {
        SpringApplication.run(LundegaardApplication.class, args);
    }

}
