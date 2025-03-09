package groupe.isi.com.gestion.etablissement.service;

import groupe.isi.com.gestion.etablissement.dto.ProfesseurDto;
import groupe.isi.com.gestion.etablissement.model.Professeur;
import java.util.List;

public interface ProfesseurService {
    Professeur creer(ProfesseurDto professeurDto);
    ProfesseurDto modifier(Long id, ProfesseurDto professeurDto);
    void supprimer(Long id);
    ProfesseurDto trouverParId(Long id);
    List<ProfesseurDto> trouverTout();
    List<ProfesseurDto> trouverParNom(String nom);
    ProfesseurDto trouverParEmail(String email);
    ProfesseurDto trouverParMatricule(String matricule);
    List<ProfesseurDto> trouverParSpecialite(String specialite);
} 