package groupe.isi.com.gestion.etablissement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
public class Classe {
    // Getters et Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nom;

    @Setter
    @Getter
    @Column(nullable = false)
    private String niveau;

    @Setter
    @Getter
    @Column(name = "annee_scolaire", nullable = false)
    private String anneeScolaire;

    @Setter
    @Getter
    @Column(nullable = false)
    private Integer capacite;

    @Setter
    @Getter
    @Column(name = "nombre_etudiants")
    private Integer nombreEtudiants = 0;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etudiant> etudiants = new ArrayList<>();


}