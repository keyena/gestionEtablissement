package groupe.isi.com.gestion.etablissement.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "emplois_du_temps")
public class EmploiDuTemps {
    // Getters et Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String salle;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
} 