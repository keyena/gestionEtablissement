package groupe.isi.com.gestion.etablissement.dto;

import lombok.Getter;

public class ClasseDto {
    @Getter
    private Long id;
    @Getter
    private String nom;
    @Getter
    private String niveau;
    @Getter
    private String anneeScolaire;
    @Getter
    private Integer capacite;
    private Integer nombreEtudiants;

    public ClasseDto() {}

    public ClasseDto(Long id, String nom, String niveau, String anneeScolaire, 
                    Integer capacite, Integer nombreEtudiants) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
        this.anneeScolaire = anneeScolaire;
        this.capacite = capacite;
        this.nombreEtudiants = nombreEtudiants;
    }

    public void setId(Long id) { this.id = id; }

    public void setNom(String nom) { this.nom = nom; }

    public void setNiveau(String niveau) { this.niveau = niveau; }

    public void setAnneeScolaire(String anneeScolaire) { this.anneeScolaire = anneeScolaire; }

    public void setCapacite(Integer capacite) { this.capacite = capacite; }

    public void setNombreEtudiants(Integer nombreEtudiants) { this.nombreEtudiants = nombreEtudiants; }
}