package groupe.isi.com.gestion.etablissement.config;

import groupe.isi.com.gestion.etablissement.repository.ClasseRepository;
import groupe.isi.com.gestion.etablissement.service.ClasseService;
import groupe.isi.com.gestion.etablissement.service.impl.ClasseServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public ClasseService classeService(ClasseRepository classeRepository) {
        return new ClasseServiceImpl(classeRepository);
    }
} 