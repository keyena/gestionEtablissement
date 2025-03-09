package groupe.isi.com.gestion.etablissement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmploiDuTempsDto {
    // Getters et Setters
    private Long id;
    private Long coursId;
    private String nomCours;
    private Long classeId;
    private String nomClasse;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String salle;

    public void setId(Long id) { this.id = id; }

    public void setCoursId(Long coursId) { this.coursId = coursId; }

    public void setNomCours(String nomCours) { this.nomCours = nomCours; }

    public void setClasseId(Long classeId) { this.classeId = classeId; }

    public void setNomClasse(String nomClasse) { this.nomClasse = nomClasse; }

    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }

    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }

    public void setSalle(String salle) { this.salle = salle; }
}