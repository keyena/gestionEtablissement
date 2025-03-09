package groupe.isi.com.gestion.etablissement.controller;

import groupe.isi.com.gestion.etablissement.dto.EtudiantDto;
import groupe.isi.com.gestion.etablissement.service.EtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@Tag(name = "Etudiants", description = "API de gestion des étudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    @Autowired
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel étudiant")
    public ResponseEntity<EtudiantDto> creer(@RequestBody EtudiantDto etudiantDto) {
        return ResponseEntity.ok(etudiantService.creer(etudiantDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un étudiant existant")
    public ResponseEntity<EtudiantDto> modifier(@PathVariable Long id, @RequestBody EtudiantDto etudiantDto) {
        return ResponseEntity.ok(etudiantService.modifier(id, etudiantDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un étudiant")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        etudiantService.supprimer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver un étudiant par son ID")
    public ResponseEntity<EtudiantDto> trouverParId(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.trouverParId(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les étudiants")
    public ResponseEntity<List<EtudiantDto>> trouverTout() {
        return ResponseEntity.ok(etudiantService.trouverTout());
    }

    @GetMapping("/classe/{classeId}")
    @Operation(summary = "Lister les étudiants d'une classe")
    public ResponseEntity<List<EtudiantDto>> trouverParClasse(@PathVariable Long classeId) {
        return ResponseEntity.ok(etudiantService.trouverParClasse(classeId));
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<EtudiantDto>> rechercherEtudiants(@RequestParam String terme) {
        return ResponseEntity.ok(etudiantService.rechercherParNomOuPrenom(terme));
    }
} 