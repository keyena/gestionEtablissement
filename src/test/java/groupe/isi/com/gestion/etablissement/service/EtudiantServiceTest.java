package groupe.isi.com.gestion.etablissement.service;

import groupe.isi.com.gestion.etablissement.dto.EtudiantDto;
import groupe.isi.com.gestion.etablissement.model.Classe;
import groupe.isi.com.gestion.etablissement.model.Etudiant;
import groupe.isi.com.gestion.etablissement.repository.EtudiantRepository;
import groupe.isi.com.gestion.etablissement.repository.ClasseRepository;
import groupe.isi.com.gestion.etablissement.service.impl.EtudiantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ClasseRepository classeRepository;

    private EtudiantService etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        etudiantService = new EtudiantServiceImpl(etudiantRepository, classeRepository);
    }

    @Test
    void creer_DevraitRetournerEtudiantDto() {
        // Arrange
        EtudiantDto dto = new EtudiantDto();
        dto.setNom("Diop");
        dto.setPrenom("Fatou");
        dto.setNumeroEtudiant("2024-001");
        dto.setDateNaissance(String.valueOf(LocalDate.of(2002, 5, 15)));
        dto.setClasseId(1L);

        Classe classe = new Classe();
        classe.setId(1L);
        classe.setNom("L3 GL");
        classe.setNiveau("Licence 3");

        Etudiant etudiant = new Etudiant();
        etudiant.setId(1L);
        etudiant.setNom(dto.getNom());
        etudiant.setNumeroEtudiant(dto.getNumeroEtudiant());
        etudiant.setClasse(classe);

        when(classeRepository.findById(dto.getClasseId())).thenReturn(Optional.of(classe));
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        EtudiantDto result = etudiantService.creer(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getNom(), result.getNom());
        verify(etudiantRepository).save(any(Etudiant.class));
        verify(classeRepository).findById(dto.getClasseId());
    }

    @Test
    void creerEtudiant_DevraitReussir() {
        // Arrange
        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.setNumeroEtudiant("2024001");
        etudiantDto.setNom("Doe");
        etudiantDto.setPrenom("John");
        // Convertir la date en String avec le bon format
        String dateNaissance = LocalDate.of(2000, 1, 1)
            .format(DateTimeFormatter.ISO_LOCAL_DATE);
        etudiantDto.setDateNaissance(dateNaissance);
        etudiantDto.setClasseId(1L);

        // ... reste du test ...
    }
} 