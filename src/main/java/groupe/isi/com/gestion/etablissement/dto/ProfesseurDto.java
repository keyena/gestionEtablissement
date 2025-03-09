package groupe.isi.com.gestion.etablissement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProfesseurDto {
    // Getters
    private Long id;
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String specialite;

    // Constructeurs
    public ProfesseurDto() {}

    public ProfesseurDto(Long id, String matricule, String email) {
        this.id = id;
        this.matricule = matricule;
        this.email = email;
    }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
}