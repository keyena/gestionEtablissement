package groupe.isi.com.gestion.etablissement.utils;

import groupe.isi.com.gestion.etablissement.dto.ProfesseurDto;
import groupe.isi.com.gestion.etablissement.model.Professeur;

public class TestUtils {
    public static Professeur createProfesseur() {
        return Professeur.builder()
                .matricule("PROF001")
                .email("prof001@example.com")
                .build();
    }

    public static ProfesseurDto createProfesseurDto() {
        return ProfesseurDto.builder()
                .matricule("PROF001")
                .email("prof001@example.com")
                .build();
    }
} 