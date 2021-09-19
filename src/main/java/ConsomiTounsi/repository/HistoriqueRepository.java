package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique,Long> {

    @Query("SELECT COUNT(h) FROM Historique h")
    long getNombreHistorique();



}
