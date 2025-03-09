package groupe.isi.com.gestion.etablissement.service.impl;

import groupe.isi.com.gestion.etablissement.dto.EtudiantDto;
import groupe.isi.com.gestion.etablissement.model.Classe;
import groupe.isi.com.gestion.etablissement.model.Etudiant;
import groupe.isi.com.gestion.etablissement.repository.ClasseRepository;
import groupe.isi.com.gestion.etablissement.repository.EtudiantRepository;
import groupe.isi.com.gestion.etablissement.service.EtudiantService;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtudiantServiceImpl implements EtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, ClasseRepository classeRepository) {
        this.etudiantRepository = etudiantRepository;
        this.classeRepository = classeRepository;
    }

    @Override
    public EtudiantDto creer(EtudiantDto etudiantDto) {
        if (etudiantRepository.existsByNumeroEtudiant(etudiantDto.getNumeroEtudiant())) {
            throw new IllegalArgumentException("Un étudiant existe déjà avec ce numéro");
        }

        Classe classe = classeRepository.findById(etudiantDto.getClasseId())
            .orElseThrow(() -> new EntityNotFoundException("Classe non trouvée"));

        Etudiant etudiant = new Etudiant();
        etudiant.setNumeroEtudiant(etudiantDto.getNumeroEtudiant());
        etudiant.setNom(etudiantDto.getNom());
        etudiant.setPrenom(etudiantDto.getPrenom());
        etudiant.setDateNaissance(etudiantDto.getDateNaissance());
        etudiant.setClasse(classe);

        return mapToDto(etudiantRepository.save(etudiant));
    }

    @Override
    public EtudiantDto modifier(Long id, EtudiantDto etudiantDto) {
        Etudiant etudiant = etudiantRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé"));

        Classe classe = classeRepository.findById(etudiantDto.getClasseId())
            .orElseThrow(() -> new EntityNotFoundException("Classe non trouvée"));

        etudiant.setNom(etudiantDto.getNom());
        etudiant.setPrenom(etudiantDto.getPrenom());
        etudiant.setDateNaissance(etudiantDto.getDateNaissance());
        etudiant.setClasse(classe);

        return mapToDto(etudiantRepository.save(etudiant));
    }

    @Override
    public void supprimer(Long id) {
        if (!etudiantRepository.existsById(id)) {
            throw new EntityNotFoundException("Étudiant non trouvé");
        }
        etudiantRepository.deleteById(id);
    }

    @Override
    public EtudiantDto trouverParId(Long id) {
        return etudiantRepository.findById(id)
            .map(this::mapToDto)
            .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé"));
    }

    @Override
    public List<EtudiantDto> trouverTout() {
        return etudiantRepository.findAll().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<EtudiantDto> trouverParClasse(Long classeId) {
        return etudiantRepository.findByClasseId(classeId).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<EtudiantDto> trouverParNom(String nom) {
        return etudiantRepository.findByNomContainingOrPrenomContaining(nom, nom).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public EtudiantDto trouverParNumeroEtudiant(String numeroEtudiant) {
        return etudiantRepository.findByNumeroEtudiant(numeroEtudiant)
            .map(this::mapToDto)
            .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé"));
    }

    @Override
    public List<EtudiantDto> rechercherParNomOuPrenom(String recherche) {
        return etudiantRepository.findByNomContainingOrPrenomContaining(recherche, recherche)
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private EtudiantDto mapToDto(Etudiant etudiant) {
        EtudiantDto dto = new EtudiantDto();
        dto.setId(etudiant.getId());
        dto.setNumeroEtudiant(etudiant.getNumeroEtudiant());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setDateNaissance(etudiant.getDateNaissance());
        dto.setClasseId(etudiant.getClasse().getId());
        dto.setNomClasse(etudiant.getClasse().getNom());
        return dto;
    }
} 