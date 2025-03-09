package groupe.isi.com.gestion.etablissement.repository;

import groupe.isi.com.gestion.etablissement.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findByClasseId(Long classeId);
    Optional<Etudiant> findByNumeroEtudiant(String numeroEtudiant);
    boolean existsByNumeroEtudiant(String numeroEtudiant);
    List<Etudiant> findByNomContainingIgnoreCase(String nom);
    List<Etudiant> findByNomContainingOrPrenomContaining(String nom, String prenom);
} 