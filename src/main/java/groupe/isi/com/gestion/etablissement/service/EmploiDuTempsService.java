package groupe.isi.com.gestion.etablissement.service;

import groupe.isi.com.gestion.etablissement.dto.EmploiDuTempsDto;
import java.time.LocalDateTime;
import java.util.List;

public interface EmploiDuTempsService {
    EmploiDuTempsDto creer(EmploiDuTempsDto emploiDuTempsDto);
    EmploiDuTempsDto modifier(Long id, EmploiDuTempsDto emploiDuTempsDto);
    void supprimer(Long id);
    EmploiDuTempsDto trouverParId(Long id);
    List<EmploiDuTempsDto> trouverTout();
    List<EmploiDuTempsDto> trouverParCours(Long coursId);
    List<EmploiDuTempsDto> trouverParClasse(Long classeId);
    List<EmploiDuTempsDto> trouverParProfesseur(Long professeurId);
    List<EmploiDuTempsDto> trouverParPeriode(LocalDateTime debut, LocalDateTime fin);
} 