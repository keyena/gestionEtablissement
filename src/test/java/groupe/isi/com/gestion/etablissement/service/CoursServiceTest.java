package groupe.isi.com.gestion.etablissement.service;

import groupe.isi.com.gestion.etablissement.dto.CoursDto;
import groupe.isi.com.gestion.etablissement.model.Cours;
import groupe.isi.com.gestion.etablissement.model.Professeur;
import groupe.isi.com.gestion.etablissement.repository.CoursRepository;
import groupe.isi.com.gestion.etablissement.repository.ProfesseurRepository;
import groupe.isi.com.gestion.etablissement.service.impl.CoursServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoursServiceTest {

    @Mock
    private CoursRepository coursRepository;

    @Mock
    private ProfesseurRepository professeurRepository;

    private CoursService coursService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        coursService = new CoursServiceImpl(coursRepository, professeurRepository);
    }

    @Test
    void creer_DevraitRetournerCoursDto() {
        // Arrange
        Long professeurId = 1L;
        
        Professeur professeur = new Professeur();
        professeur.setId(professeurId);
        professeur.setNom("Diop");
        professeur.setPrenom("Fatou");
        
        CoursDto dto = new CoursDto();
        dto.setNomCours("Java Programming");
        dto.setCodeCours("JAVA-001");
        dto.setCoefficient(4);
        dto.setProfesseurId(professeurId);

        Cours cours = new Cours();
        cours.setId(1L);
        cours.setNomCours(dto.getNomCours());
        cours.setCodeCours(dto.getCodeCours());
        cours.setCoefficient(dto.getCoefficient());
        cours.setProfesseur(professeur);

        when(professeurRepository.findById(professeurId)).thenReturn(Optional.of(professeur));
        when(coursRepository.save(any(Cours.class))).thenReturn(cours);

        // Act
        CoursDto result = coursService.creer(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getNomCours(), result.getNomCours());
        assertEquals(dto.getCodeCours(), result.getCodeCours());
        assertEquals(dto.getCoefficient(), result.getCoefficient());
        assertEquals(professeurId, result.getProfesseurId());
        assertEquals("Diop Fatou", result.getNomProfesseur());
        
        verify(professeurRepository).findById(professeurId);
        verify(coursRepository).save(any(Cours.class));
    }
} 