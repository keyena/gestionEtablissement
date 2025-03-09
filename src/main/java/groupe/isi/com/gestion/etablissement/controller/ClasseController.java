package groupe.isi.com.gestion.etablissement.controller;

import groupe.isi.com.gestion.etablissement.dto.ClasseDto;
import groupe.isi.com.gestion.etablissement.service.ClasseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Tag(name = "Classe", description = "API de gestion des classes")
public class ClasseController {

    private final ClasseService classeService;

    @Autowired
    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle classe")
    public ResponseEntity<ClasseDto> creer(@RequestBody ClasseDto classeDto) {
        return ResponseEntity.ok(classeService.creer(classeDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une classe existante")
    public ResponseEntity<ClasseDto> modifier(@PathVariable Long id, @RequestBody ClasseDto classeDto) {
        return ResponseEntity.ok(classeService.modifier(id, classeDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une classe")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        classeService.supprimer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Trouver une classe par son ID")
    public ResponseEntity<ClasseDto> trouverParId(@PathVariable Long id) {
        return ResponseEntity.ok(classeService.trouverParId(id));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les classes")
    public ResponseEntity<List<ClasseDto>> trouverTout() {
        return ResponseEntity.ok(classeService.trouverTout());
    }

    @GetMapping("/niveau/{niveau}")
    @Operation(summary = "Trouver les classes par niveau")
    public ResponseEntity<List<ClasseDto>> trouverParNiveau(@PathVariable String niveau) {
        return ResponseEntity.ok(classeService.trouverParNiveau(niveau));
    }

    @GetMapping("/annee-scolaire/{anneeScolaire}")
    @Operation(summary = "Trouver les classes par année scolaire")
    public ResponseEntity<List<ClasseDto>> trouverParAnneeScolaire(@PathVariable String anneeScolaire) {
        return ResponseEntity.ok(classeService.trouverParAnneeScolaire(anneeScolaire));
    }
} 