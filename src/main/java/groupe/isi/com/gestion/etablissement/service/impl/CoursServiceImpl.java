package groupe.isi.com.gestion.etablissement.service.impl;

import groupe.isi.com.gestion.etablissement.dto.CoursDto;
import groupe.isi.com.gestion.etablissement.model.Cours;
import groupe.isi.com.gestion.etablissement.model.Professeur;
import groupe.isi.com.gestion.etablissement.repository.CoursRepository;
import groupe.isi.com.gestion.etablissement.repository.ProfesseurRepository;
import groupe.isi.com.gestion.etablissement.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;
    private final ProfesseurRepository professeurRepository;

    @Autowired
    public CoursServiceImpl(CoursRepository coursRepository, ProfesseurRepository professeurRepository) {
        this.coursRepository = coursRepository;
        this.professeurRepository = professeurRepository;
    }

    @Override
    public CoursDto creer(CoursDto dto) {
        Professeur professeur = professeurRepository.findById(dto.getProfesseurId())
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé"));

        Cours cours = new Cours();
        cours.setNomCours(dto.getNomCours());
        cours.setCodeCours(dto.getCodeCours());
        cours.setCoefficient(dto.getCoefficient());
        cours.setProfesseur(professeur);

        cours = coursRepository.save(cours);
        return mapToDto(cours);
    }

    @Override
    public CoursDto modifier(Long id, CoursDto dto) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé"));

        Professeur professeur = professeurRepository.findById(dto.getProfesseurId())
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé"));

        cours.setNomCours(dto.getNomCours());
        cours.setCodeCours(dto.getCodeCours());
        cours.setCoefficient(dto.getCoefficient());
        cours.setProfesseur(professeur);

        cours = coursRepository.save(cours);
        return mapToDto(cours);
    }

    @Override
    public void supprimer(Long id) {
        coursRepository.deleteById(id);
    }

    @Override
    public CoursDto trouverParId(Long id) {
        return coursRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé"));
    }

    @Override
    public List<CoursDto> trouverTout() {
        return coursRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CoursDto> trouverParProfesseur(Long professeurId) {
        return coursRepository.findByProfesseurId(professeurId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CoursDto mapToDto(Cours cours) {
        CoursDto dto = new CoursDto();
        dto.setId(cours.getId());
        dto.setNomCours(cours.getNomCours());
        dto.setCodeCours(cours.getCodeCours());
        dto.setCoefficient(cours.getCoefficient());
        dto.setProfesseurId(cours.getProfesseur().getId());
        dto.setNomProfesseur(cours.getProfesseur().getNom() + " " + cours.getProfesseur().getPrenom());
        return dto;
    }
} 