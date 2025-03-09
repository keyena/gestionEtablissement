package groupe.isi.com.gestion.etablissement.service;

import groupe.isi.com.gestion.etablissement.dto.ClasseDto;
import groupe.isi.com.gestion.etablissement.model.Classe;
import groupe.isi.com.gestion.etablissement.repository.ClasseRepository;
import groupe.isi.com.gestion.etablissement.service.impl.ClasseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClasseServiceTest {

    @Mock
    private ClasseRepository classeRepository;

    private ClasseService classeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        classeService = new ClasseServiceImpl(classeRepository);
    }

    @Test
    void creer_DevraitRetournerClasseDto() {
        // Arrange
        ClasseDto dto = new ClasseDto();
        dto.setNom("L3 GL");
        dto.setNiveau("Licence 3");
        dto.setAnneeScolaire("2023-2024");
        dto.setCapacite(40);

        Classe classe = new Classe();
        classe.setId(1L);
        classe.setNom(dto.getNom());
        classe.setNiveau(dto.getNiveau());
        classe.setAnneeScolaire(dto.getAnneeScolaire());
        classe.setCapacite(dto.getCapacite());

        when(classeRepository.save(any(Classe.class))).thenReturn(classe);

        // Act
        ClasseDto result = classeService.creer(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getNom(), result.getNom());
        verify(classeRepository).save(any(Classe.class));
    }

    @Test
    void trouverParId_DevraitRetournerClasseDto() {
        // Arrange
        Long id = 1L;
        Classe classe = new Classe();
        classe.setId(id);
        classe.setNom("L3 GL");

        when(classeRepository.findById(id)).thenReturn(Optional.of(classe));

        // Act
        ClasseDto result = classeService.trouverParId(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }
} 