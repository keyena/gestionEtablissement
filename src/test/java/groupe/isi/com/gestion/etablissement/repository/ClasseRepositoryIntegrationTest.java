package groupe.isi.com.gestion.etablissement.repository;

import groupe.isi.com.gestion.etablissement.model.Classe;
import groupe.isi.com.gestion.etablissement.GestionEtablissementApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(classes = GestionEtablissementApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class ClasseRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClasseRepository classeRepository;

    @Test
    void findByNiveauContaining_DevraitTrouverClasses() {
        // Arrange
        Classe classe = new Classe();
        classe.setNiveau("Niveau1");
        classe.setNom("Classe Test");
        classe.setCapacite(30);
        classe.setNombreEtudiants(20);
        classe.setAnneeScolaire("2023-2024");
        
        entityManager.persist(classe);
        entityManager.flush();

        // Act
        List<Classe> classes = classeRepository.findByNiveauContainingIgnoreCase("niveau");

        // Assert
        assertNotNull(classes);
        assertThat(classes).isNotEmpty();
        assertThat(classes.get(0).getNiveau()).isEqualToIgnoringCase("Niveau1");
    }

    private void assertNotNull(List<Classe> classes) {
        assertThat(classes).isNotNull();
    }
} 