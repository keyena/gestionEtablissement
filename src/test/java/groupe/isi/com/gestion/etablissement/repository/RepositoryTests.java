package groupe.isi.com.gestion.etablissement.repository;

import groupe.isi.com.gestion.etablissement.mapper.ProfesseurMapper;
import groupe.isi.com.gestion.etablissement.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
public class
RepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @MockBean
    private ProfesseurMapper professeurMapper;

    @BeforeEach
    void setUp() {
        // Vérifier que la base de données est bien initialisée
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void professeurRepository_DevraitFonctionnerCorrectement() {
        // Arrangement
        Professeur professeur = Professeur.builder()
                .matricule("PROF001")
                .email("prof001@example.com")
                .nom("Dupont")
                .prenom("Jean")
                .build();

        // Action
        Professeur savedProfesseur = professeurRepository.save(professeur);

        // Assertion
        assertThat(savedProfesseur).isNotNull();
        assertThat(savedProfesseur.getId()).isNotNull();
        assertThat(savedProfesseur.getMatricule()).isEqualTo("PROF001");
        assertThat(savedProfesseur.getEmail()).isEqualTo("prof001@example.com");
        assertThat(savedProfesseur.getNom()).isEqualTo("Dupont");
    }

    @Test
    void classeRepository_DevraitFonctionnerCorrectement() {
        // Arrange
        Classe classe = new Classe();
        classe.setNom("L3 GL");
        classe.setNiveau("Licence 3");
        classe.setAnneeScolaire("2023-2024");
        classe.setCapacite(30);
        
        // Act
        classe = classeRepository.save(classe);
        
        // Assert
        assertTrue(classeRepository.findById(classe.getId()).isPresent());
    }

    @Test
    void etudiantRepository_DevraitFonctionnerCorrectement() {
        // Arrange
        Classe classe = new Classe();
        classe.setNom("L3 GL");
        classe.setNiveau("Licence 3");
        classe.setAnneeScolaire("2023-2024");
        classe.setCapacite(30);
        entityManager.persist(classe);

        Etudiant etudiant = new Etudiant();
        etudiant.setNom("Diop");
        etudiant.setPrenom("Fatou");
        etudiant.setNumeroEtudiant("ETU001");
        etudiant.setClasse(classe);
        
        // Act
        etudiant = etudiantRepository.save(etudiant);
        
        // Assert
        assertTrue(etudiantRepository.findById(etudiant.getId()).isPresent());
    }
} 