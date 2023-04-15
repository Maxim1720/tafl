package kz.trankwilizator.tafl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SpringBootApplication
@EnableJpaRepositories
@RepositoryRestResource
public class TaflApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaflApplication.class, args);
    }

}
