package groupe.isi.com.gestion.etablissement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "cours")
@Setter
@NoArgsConstructor
public class Cours {
    // Getters et Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomCours;
    private String codeCours;
    private Integer coefficient;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public void setCodeCours(String codeCours) {
        this.codeCours = codeCours;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }
} 