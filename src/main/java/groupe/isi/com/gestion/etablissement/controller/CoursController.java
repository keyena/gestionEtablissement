package groupe.isi.com.gestion.etablissement.controller;

import groupe.isi.com.gestion.etablissement.dto.CoursDto;
import groupe.isi.com.gestion.etablissement.service.CoursService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
@Tag(name = "Cours", description = "API de gestion des cours")
public class CoursController {

    private final CoursService coursService;

    @Autowired
    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau cours")
    public ResponseEntity<CoursDto> creer(@RequestBody CoursDto coursDto) {
        return ResponseEntity.ok(coursService.creer(coursDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un cours existant")
    public ResponseEntity<CoursDto> modifier(@PathVariable Long id, @RequestBody CoursDto coursDto) {
        return ResponseEntity.ok(coursService.modifier(id, coursDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un cours")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        coursService.supprimer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver un cours par son ID")
    public ResponseEntity<CoursDto> trouverParId(@PathVariable Long id) {
        return ResponseEntity.ok(coursService.trouverParId(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les cours")
    public ResponseEntity<List<CoursDto>> trouverTout() {
        return ResponseEntity.ok(coursService.trouverTout());
    }

    @GetMapping("/professeur/{professeurId}")
    @Operation(summary = "Lister les cours d'un professeur")
    public ResponseEntity<List<CoursDto>> trouverParProfesseur(@PathVariable Long professeurId) {
        return ResponseEntity.ok(coursService.trouverParProfesseur(professeurId));
    }
} 