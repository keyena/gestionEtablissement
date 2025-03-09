package groupe.isi.com.gestion.etablissement.service.impl;

import groupe.isi.com.gestion.etablissement.dto.ProfesseurDto;
import groupe.isi.com.gestion.etablissement.model.Professeur;
import groupe.isi.com.gestion.etablissement.repository.ProfesseurRepository;
import groupe.isi.com.gestion.etablissement.service.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesseurServiceImpl implements ProfesseurService {
    private final ProfesseurRepository professeurRepository;

    @Autowired
    public ProfesseurServiceImpl(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    @Override
    public Professeur creer(ProfesseurDto dto) {
        Professeur professeur = new Professeur(dto.getMatricule());
        return professeurRepository.save(professeur);
    }

    @Override
    public ProfesseurDto modifier(Long id, ProfesseurDto dto) {
        Professeur professeur = professeurRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Professeur non trouvé"));

        professeur.setNom(dto.getNom());
        professeur.setPrenom(dto.getPrenom());
        professeur.setEmail(dto.getEmail());
        professeur.setTelephone(dto.getTelephone());
        professeur.setSpecialite(dto.getSpecialite());

        return mapToDto(professeurRepository.save(professeur));
    }

    @Override
    public void supprimer(Long id) {
        if (!professeurRepository.existsById(id)) {
            throw new EntityNotFoundException("Professeur non trouvé");
        }
        professeurRepository.deleteById(id);
    }

    @Override
    public ProfesseurDto trouverParId(Long id) {
        return professeurRepository.findById(id)
            .map(this::mapToDto)
            .orElseThrow(() -> new EntityNotFoundException("Professeur non trouvé"));
    }

    @Override
    public List<ProfesseurDto> trouverTout() {
        return professeurRepository.findAll().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProfesseurDto> trouverParNom(String nom) {
        return professeurRepository.findByNomContainingIgnoreCase(nom).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public ProfesseurDto trouverParEmail(String email) {
        return professeurRepository.findByEmail(email)
            .map(this::mapToDto)
            .orElseThrow(() -> new EntityNotFoundException("Professeur non trouvé"));
    }

    @Override
    public ProfesseurDto trouverParMatricule(String matricule) {
        return professeurRepository.findByMatricule(matricule)
            .map(this::mapToDto)
            .orElseThrow(() -> new EntityNotFoundException("Professeur non trouvé"));
    }

    @Override
    public List<ProfesseurDto> trouverParSpecialite(String specialite) {
        return professeurRepository.findBySpecialiteContainingIgnoreCase(specialite)
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private ProfesseurDto mapToDto(Professeur professeur) {
        ProfesseurDto dto = new ProfesseurDto();
        dto.setId(professeur.getId());
        dto.setMatricule(professeur.getMatricule());
        dto.setNom(professeur.getNom());
        dto.setPrenom(professeur.getPrenom());
        dto.setEmail(professeur.getEmail());
        dto.setTelephone(professeur.getTelephone());
        dto.setSpecialite(professeur.getSpecialite());
        return dto;
    }
} 