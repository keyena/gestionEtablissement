package groupe.isi.com.gestion.etablissement.repository;

import groupe.isi.com.gestion.etablissement.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    boolean existsByEmail(String email);
    boolean existsByMatricule(String matricule);
    Optional<Professeur> findByEmail(String email);
    Optional<Professeur> findByMatricule(String matricule);
    List<Professeur> findByNomContainingIgnoreCase(String nom);
    List<Professeur> findBySpecialiteContainingIgnoreCase(String specialite);
} 