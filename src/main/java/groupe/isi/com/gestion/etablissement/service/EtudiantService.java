package groupe.isi.com.gestion.etablissement.service;

import groupe.isi.com.gestion.etablissement.dto.EtudiantDto;
import java.util.List;

public interface EtudiantService {
    EtudiantDto creer(EtudiantDto etudiantDto);
    EtudiantDto modifier(Long id, EtudiantDto etudiantDto);
    void supprimer(Long id);
    EtudiantDto trouverParId(Long id);
    List<EtudiantDto> trouverTout();
    List<EtudiantDto> trouverParClasse(Long classeId);
    EtudiantDto trouverParNumeroEtudiant(String numeroEtudiant);
    List<EtudiantDto> trouverParNom(String nom);
    List<EtudiantDto> rechercherParNomOuPrenom(String recherche);
} 