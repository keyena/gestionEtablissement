package groupe.isi.com.gestion.etablissement.service.impl;

import groupe.isi.com.gestion.etablissement.dto.ClasseDto;
import groupe.isi.com.gestion.etablissement.model.Classe;
import groupe.isi.com.gestion.etablissement.repository.ClasseRepository;
import groupe.isi.com.gestion.etablissement.service.ClasseService;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseServiceImpl(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    @Override
    public ClasseDto creer(ClasseDto classeDto) {
        Classe classe = new Classe();
        classe.setNom(classeDto.getNom());
        classe.setNiveau(classeDto.getNiveau());
        classe.setAnneeScolaire(classeDto.getAnneeScolaire());
        classe.setCapacite(classeDto.getCapacite());
        classe.setNombreEtudiants(0);
        
        return mapToDto(classeRepository.save(classe));
    }

    @Override
    public ClasseDto modifier(Long id, ClasseDto classeDto) {
        Classe classe = classeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Classe non trouvée avec l'ID : " + id));
        
        classe.setNom(classeDto.getNom());
        classe.setNiveau(classeDto.getNiveau());
        classe.setAnneeScolaire(classeDto.getAnneeScolaire());
        classe.setCapacite(classeDto.getCapacite());
        
        return mapToDto(classeRepository.save(classe));
    }

    @Override
    public void supprimer(Long id) {
        if (!classeRepository.existsById(id)) {
            throw new EntityNotFoundException("Classe non trouvée avec l'ID : " + id);
        }
        classeRepository.deleteById(id);
    }

    @Override
    public ClasseDto trouverParId(Long id) {
        return classeRepository.findById(id)
            .map(this::mapToDto)
            .orElseThrow(() -> new EntityNotFoundException("Classe non trouvée avec l'ID : " + id));
    }

    @Override
    public List<ClasseDto> trouverTout() {
        return classeRepository.findAll().stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ClasseDto> trouverParAnneeScolaire(String anneeScolaire) {
        return classeRepository.findByAnneeScolaire(anneeScolaire).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ClasseDto> trouverParNiveau(String niveau) {
        return classeRepository.findByNiveau(niveau).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private ClasseDto mapToDto(Classe classe) {
        ClasseDto dto = new ClasseDto();
        dto.setId(classe.getId());
        dto.setNom(classe.getNom());
        dto.setNiveau(classe.getNiveau());
        dto.setAnneeScolaire(classe.getAnneeScolaire());
        dto.setCapacite(classe.getCapacite());
        dto.setNombreEtudiants(classe.getNombreEtudiants());
        return dto;
    }
} 