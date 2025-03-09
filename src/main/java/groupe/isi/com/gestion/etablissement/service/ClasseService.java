package groupe.isi.com.gestion.etablissement.service;

import groupe.isi.com.gestion.etablissement.dto.ClasseDto;
import java.util.List;

public interface ClasseService {
    ClasseDto creer(ClasseDto classeDto);
    ClasseDto modifier(Long id, ClasseDto classeDto);
    void supprimer(Long id);
    ClasseDto trouverParId(Long id);
    List<ClasseDto> trouverTout();
    List<ClasseDto> trouverParAnneeScolaire(String anneeScolaire);
    List<ClasseDto> trouverParNiveau(String niveau);
} 