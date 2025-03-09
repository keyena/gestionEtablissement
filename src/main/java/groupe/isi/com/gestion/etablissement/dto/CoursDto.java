package groupe.isi.com.gestion.etablissement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursDto {
    // Getters et Setters
    private Long id;
    private String nomCours;
    private String codeCours;
    private Integer coefficient;
    private Long professeurId;
    private String nomProfesseur;

    public void setId(Long id) { this.id = id; }

    public void setNomCours(String nomCours) { this.nomCours = nomCours; }

    public void setCodeCours(String codeCours) { this.codeCours = codeCours; }

    public void setCoefficient(Integer coefficient) { this.coefficient = coefficient; }

    public void setProfesseurId(Long professeurId) { this.professeurId = professeurId; }

    public void setNomProfesseur(String nomProfesseur) { this.nomProfesseur = nomProfesseur; }
}