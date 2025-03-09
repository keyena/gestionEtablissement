package groupe.isi.com.gestion.etablissement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDto {
    // Getters et Setters
    private Long id;
    private String numeroEtudiant;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private Long classeId;
    private String nomClasse;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setClasseId(Long classeId) {
        this.classeId = classeId;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }
}