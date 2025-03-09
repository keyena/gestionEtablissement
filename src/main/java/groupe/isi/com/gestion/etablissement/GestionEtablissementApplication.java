package groupe.isi.com.gestion.etablissement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "groupe.isi.com.gestion.etablissement.model")
@EnableJpaRepositories(basePackages = "groupe.isi.com.gestion.etablissement.repository")
public class GestionEtablissementApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionEtablissementApplication.class, args);
    }
} 