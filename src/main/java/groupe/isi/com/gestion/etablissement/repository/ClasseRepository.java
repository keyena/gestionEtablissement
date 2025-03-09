package groupe.isi.com.gestion.etablissement.repository;

import groupe.isi.com.gestion.etablissement.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    List<Classe> findByAnneeScolaire(String anneeScolaire);
    List<Classe> findByNiveau(String niveau);
    @Query("SELECT c FROM Classe c WHERE LOWER(c.niveau) LIKE LOWER(CONCAT('%', :niveau, '%'))")
    List<Classe> findByNiveauContainingIgnoreCase(@Param("niveau") String niveau);
}