package groupe.isi.com.gestion.etablissement.service;

import groupe.isi.com.gestion.etablissement.dto.CoursDto;
import java.util.List;

public interface CoursService {
    CoursDto creer(CoursDto coursDto);
    CoursDto modifier(Long id, CoursDto coursDto);
    void supprimer(Long id);
    CoursDto trouverParId(Long id);
    List<CoursDto> trouverTout();
    List<CoursDto> trouverParProfesseur(Long professeurId);
} 