package groupe.isi.com.gestion.etablissement.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Entity
@Table(name = "professeurs")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professeur {

    // Getters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String matricule;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    private String telephone;
    
    private String specialite;
    
    @OneToMany(mappedBy = "professeur")
    private List<Cours> cours;

    public Professeur(String matricule) {
        this.matricule = matricule;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
} 