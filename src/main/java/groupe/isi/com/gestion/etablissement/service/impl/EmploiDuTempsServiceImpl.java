package groupe.isi.com.gestion.etablissement.service.impl;

import groupe.isi.com.gestion.etablissement.dto.EmploiDuTempsDto;
import groupe.isi.com.gestion.etablissement.model.Classe;
import groupe.isi.com.gestion.etablissement.model.Cours;
import groupe.isi.com.gestion.etablissement.model.EmploiDuTemps;
import groupe.isi.com.gestion.etablissement.repository.ClasseRepository;
import groupe.isi.com.gestion.etablissement.repository.CoursRepository;
import groupe.isi.com.gestion.etablissement.repository.EmploiDuTempsRepository;
import groupe.isi.com.gestion.etablissement.service.EmploiDuTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmploiDuTempsServiceImpl implements EmploiDuTempsService {

    private final EmploiDuTempsRepository emploiDuTempsRepository;
    private final CoursRepository coursRepository;
    private final ClasseRepository classeRepository;

    @Autowired
    public EmploiDuTempsServiceImpl(
            EmploiDuTempsRepository emploiDuTempsRepository,
            CoursRepository coursRepository,
            ClasseRepository classeRepository) {
        this.emploiDuTempsRepository = emploiDuTempsRepository;
        this.coursRepository = coursRepository;
        this.classeRepository = classeRepository;
    }

    @Override
    public EmploiDuTempsDto creer(EmploiDuTempsDto dto) {
        validateTimeSlot(dto);
        
        Cours cours = coursRepository.findById(dto.getCoursId())
                .orElseThrow(() -> new RuntimeException("Cours non trouvé"));
        
        Classe classe = classeRepository.findById(dto.getClasseId())
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));

        EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
        emploiDuTemps.setCours(cours);
        emploiDuTemps.setClasse(classe);
        emploiDuTemps.setDateDebut(dto.getDateDebut());
        emploiDuTemps.setDateFin(dto.getDateFin());
        emploiDuTemps.setSalle(dto.getSalle());

        emploiDuTemps = emploiDuTempsRepository.save(emploiDuTemps);
        return mapToDto(emploiDuTemps);
    }

    @Override
    public EmploiDuTempsDto modifier(Long id, EmploiDuTempsDto dto) {
        EmploiDuTemps emploiDuTemps = emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emploi du temps non trouvé"));

        validateTimeSlot(dto);
        
        Cours cours = coursRepository.findById(dto.getCoursId())
                .orElseThrow(() -> new RuntimeException("Cours non trouvé"));
        
        Classe classe = classeRepository.findById(dto.getClasseId())
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));

        emploiDuTemps.setCours(cours);
        emploiDuTemps.setClasse(classe);
        emploiDuTemps.setDateDebut(dto.getDateDebut());
        emploiDuTemps.setDateFin(dto.getDateFin());
        emploiDuTemps.setSalle(dto.getSalle());

        emploiDuTemps = emploiDuTempsRepository.save(emploiDuTemps);
        return mapToDto(emploiDuTemps);
    }

    @Override
    public void supprimer(Long id) {
        emploiDuTempsRepository.deleteById(id);
    }

    @Override
    public EmploiDuTempsDto trouverParId(Long id) {
        return emploiDuTempsRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Emploi du temps non trouvé"));
    }

    @Override
    public List<EmploiDuTempsDto> trouverTout() {
        return emploiDuTempsRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmploiDuTempsDto> trouverParClasse(Long classeId) {
        return emploiDuTempsRepository.findByClasseId(classeId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmploiDuTempsDto> trouverParPeriode(LocalDateTime debut, LocalDateTime fin) {
        return emploiDuTempsRepository.findByDateDebutBetween(debut, fin)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmploiDuTempsDto> trouverParProfesseur(Long professeurId) {
        List<EmploiDuTemps> emploisDuTemps = emploiDuTempsRepository.findAll().stream()
                .filter(edt -> edt.getCours().getProfesseur().getId().equals(professeurId))
                .collect(Collectors.toList());
        
        return emploisDuTemps.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmploiDuTempsDto> trouverParCours(Long coursId) {
        return emploiDuTempsRepository.findByCoursId(coursId)
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private void validateTimeSlot(EmploiDuTempsDto dto) {
        if (dto.getDateDebut().isAfter(dto.getDateFin())) {
            throw new RuntimeException("La date de début doit être antérieure à la date de fin");
        }

        boolean salleOccupee = emploiDuTempsRepository.existsBySalleAndDateDebutBetween(
                dto.getSalle(), dto.getDateDebut(), dto.getDateFin());
        
        if (salleOccupee) {
            throw new RuntimeException("La salle est déjà occupée sur ce créneau");
        }
    }

    private EmploiDuTempsDto mapToDto(EmploiDuTemps emploiDuTemps) {
        EmploiDuTempsDto dto = new EmploiDuTempsDto();
        dto.setId(emploiDuTemps.getId());
        dto.setCoursId(emploiDuTemps.getCours().getId());
        dto.setNomCours(emploiDuTemps.getCours().getNomCours());
        dto.setClasseId(emploiDuTemps.getClasse().getId());
        dto.setNomClasse(emploiDuTemps.getClasse().getNom());
        dto.setDateDebut(emploiDuTemps.getDateDebut());
        dto.setDateFin(emploiDuTemps.getDateFin());
        dto.setSalle(emploiDuTemps.getSalle());
        return dto;
    }
} 