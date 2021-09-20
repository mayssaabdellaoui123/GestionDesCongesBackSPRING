package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Historique;
import ConsomiTounsi.entities.TypeHistorique;
import ConsomiTounsi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique,Long> {

    @Query("SELECT COUNT(h) FROM Historique h")
    long getNombreHistorique();

    @Query("SELECT h FROM Historique h WHERE h.owner= :owner " )
    List<Historique> RetiveHistoriqueByOwner(@Param("owner") String owner);

    @Query("SELECT h FROM Historique h WHERE h.typehistorique= :type " )
    List<Historique> RetiveHistoriqueByType(@Param("type") TypeHistorique type);

    @Query("SELECT h FROM Historique h WHERE h.action= :action " )
    List<Historique> RetiveHistoriqueByAction(@Param("action") String action);

    @Query("SELECT h FROM Historique h WHERE h.typehistorique= :type  and h.action= :action" )
    List<Historique> RetiveHistoriqueByFiltre(@Param("type") TypeHistorique type, @Param("action") String action);






}
