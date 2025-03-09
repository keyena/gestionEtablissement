package groupe.isi.com.gestion.etablissement.controller;

import groupe.isi.com.gestion.etablissement.dto.ProfesseurDto;
import groupe.isi.com.gestion.etablissement.model.Professeur;
import groupe.isi.com.gestion.etablissement.service.ProfesseurService;
import groupe.isi.com.gestion.etablissement.mapper.ProfesseurMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/professeurs")
@Tag(name = "Professeur", description = "API de gestion des professeurs")
public class ProfesseurController {

    private final ProfesseurService professeurService;
    private final ProfesseurMapper professeurMapper;

    public ProfesseurController(ProfesseurService professeurService, ProfesseurMapper professeurMapper) {
        this.professeurService = professeurService;
        this.professeurMapper = professeurMapper;
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau professeur")
    public ResponseEntity<ProfesseurDto> creer(@RequestBody ProfesseurDto professeurDto) {
        Professeur professeur = professeurService.creer(professeurDto);
        return new ResponseEntity<>(professeurMapper.toDto(professeur), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un professeur existant")
    public ResponseEntity<ProfesseurDto> modifier(@PathVariable Long id, @RequestBody ProfesseurDto professeurDto) {
        return ResponseEntity.ok(professeurService.modifier(id, professeurDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un professeur")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        professeurService.supprimer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Lister tous les professeurs")
    public ResponseEntity<List<ProfesseurDto>> listerTout() {
        return ResponseEntity.ok(professeurService.trouverTout());
    }

    @GetMapping("/specialite/{specialite}")
    @Operation(summary = "Trouver les professeurs par spécialité")
    public ResponseEntity<List<ProfesseurDto>> trouverParSpecialite(@PathVariable String specialite) {
        return ResponseEntity.ok(professeurService.trouverParSpecialite(specialite));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver un professeur par ID")
    public ResponseEntity<ProfesseurDto> trouverParId(@PathVariable Long id) {
        return ResponseEntity.ok(professeurService.trouverParId(id));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Trouver un professeur par email")
    public ResponseEntity<ProfesseurDto> trouverParEmail(@PathVariable String email) {
        return ResponseEntity.ok(professeurService.trouverParEmail(email));
    }

    @GetMapping("/matricule/{matricule}")
    @Operation(summary = "Trouver un professeur par matricule")
    public ResponseEntity<ProfesseurDto> trouverParMatricule(@PathVariable String matricule) {
        return ResponseEntity.ok(professeurService.trouverParMatricule(matricule));
    }

    @GetMapping("/recherche")
    @Operation(summary = "Rechercher des professeurs par nom")
    public ResponseEntity<List<ProfesseurDto>> rechercherParNom(@RequestParam String nom) {
        return ResponseEntity.ok(professeurService.trouverParNom(nom));
    }

    // Méthode de conversion de Professeur vers ProfesseurDto
    private ProfesseurDto convertToDto(Professeur professeur) {
        ProfesseurDto dto = new ProfesseurDto();
        dto.setId(professeur.getId());
        dto.setMatricule(professeur.getMatricule());
        // Ajoutez d'autres mappings de propriétés si nécessaire
        return dto;
    }

    // Méthode de conversion de ProfesseurDto vers Professeur si nécessaire
    private Professeur convertToEntity(ProfesseurDto dto) {
        return new Professeur(dto.getMatricule());
    }
} 