package groupe.isi.com.gestion.etablissement.repository;

import groupe.isi.com.gestion.etablissement.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
    List<Cours> findByProfesseurId(Long professeurId);
    boolean existsByCodeCours(String codeCours);
} 