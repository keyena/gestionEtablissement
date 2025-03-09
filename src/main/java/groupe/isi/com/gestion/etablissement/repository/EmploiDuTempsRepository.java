package groupe.isi.com.gestion.etablissement.repository;

import groupe.isi.com.gestion.etablissement.model.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Long> {
    List<EmploiDuTemps> findByClasseId(Long classeId);
    List<EmploiDuTemps> findByCoursId(Long coursId);
    List<EmploiDuTemps> findByCoursProfesseurId(Long professeurId);
    List<EmploiDuTemps> findByDateDebutBetween(LocalDateTime debut, LocalDateTime fin);
    List<EmploiDuTemps> findByDateDebutBetweenOrDateFinBetween(
        LocalDateTime dateDebutStart, 
        LocalDateTime dateDebutEnd,
        LocalDateTime dateFinStart,
        LocalDateTime dateFinEnd
    );
    boolean existsBySalleAndDateDebutBetween(String salle, LocalDateTime debut, LocalDateTime fin);
} 