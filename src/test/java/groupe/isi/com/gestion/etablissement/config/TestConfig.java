package groupe.isi.com.gestion.etablissement.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@SpringBootApplication(scanBasePackages = "groupe.isi.com.gestion.etablissement")
@EntityScan(basePackages = "groupe.isi.com.gestion.etablissement.model")
@EnableJpaRepositories(basePackages = "groupe.isi.com.gestion.etablissement.repository")
public class TestConfig {
} 