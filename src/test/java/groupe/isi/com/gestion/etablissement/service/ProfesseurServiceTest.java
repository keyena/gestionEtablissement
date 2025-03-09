package groupe.isi.com.gestion.etablissement.service;

import groupe.isi.com.gestion.etablissement.dto.ProfesseurDto;
import groupe.isi.com.gestion.etablissement.model.Professeur;
import groupe.isi.com.gestion.etablissement.repository.ProfesseurRepository;
import groupe.isi.com.gestion.etablissement.service.impl.ProfesseurServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfesseurServiceTest {
    @Mock
    private ProfesseurRepository professeurRepository;

    @InjectMocks
    private ProfesseurServiceImpl professeurService;

    @Test
    void creerProfesseur_DevraitRetournerProfesseurCree() {
        // Arrange
        ProfesseurDto dto = new ProfesseurDto();
        dto.setMatricule("PROF001");

        Professeur expectedProfesseur = new Professeur(dto.getMatricule());
        when(professeurRepository.save(any(Professeur.class))).thenReturn(expectedProfesseur);

        // Act
        Professeur result = professeurService.creer(dto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getMatricule()).isEqualTo("PROF001");
    }
} 