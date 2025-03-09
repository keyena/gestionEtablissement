package groupe.isi.com.gestion.etablissement.controller;

import groupe.isi.com.gestion.etablissement.dto.EmploiDuTempsDto;
import groupe.isi.com.gestion.etablissement.service.EmploiDuTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/emplois-du-temps")
@CrossOrigin(origins = "*")
public class EmploiDuTempsController {

    private final EmploiDuTempsService emploiDuTempsService;

    @Autowired
    public EmploiDuTempsController(EmploiDuTempsService emploiDuTempsService) {
        this.emploiDuTempsService = emploiDuTempsService;
    }

    @PostMapping
    public ResponseEntity<EmploiDuTempsDto> creerEmploiDuTemps(@RequestBody EmploiDuTempsDto emploiDuTempsDto) {
        return ResponseEntity.ok(emploiDuTempsService.creer(emploiDuTempsDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmploiDuTempsDto> modifierEmploiDuTemps(@PathVariable Long id, @RequestBody EmploiDuTempsDto emploiDuTempsDto) {
        return ResponseEntity.ok(emploiDuTempsService.modifier(id, emploiDuTempsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEmploiDuTemps(@PathVariable Long id) {
        emploiDuTempsService.supprimer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmploiDuTempsDto> obtenirEmploiDuTemps(@PathVariable Long id) {
        return ResponseEntity.ok(emploiDuTempsService.trouverParId(id));
    }

    @GetMapping
    public ResponseEntity<List<EmploiDuTempsDto>> trouverTout() {
        return ResponseEntity.ok(emploiDuTempsService.trouverTout());
    }

    @GetMapping("/classe/{classeId}")
    public ResponseEntity<List<EmploiDuTempsDto>> listerParClasse(@PathVariable Long classeId) {
        return ResponseEntity.ok(emploiDuTempsService.trouverParClasse(classeId));
    }

    @GetMapping("/professeur/{professeurId}")
    public ResponseEntity<List<EmploiDuTempsDto>> trouverParProfesseur(@PathVariable Long professeurId) {
        return ResponseEntity.ok(emploiDuTempsService.trouverParProfesseur(professeurId));
    }

    @GetMapping("/periode")
    public ResponseEntity<List<EmploiDuTempsDto>> trouverParPeriode(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(emploiDuTempsService.trouverParPeriode(debut, fin));
    }
} 