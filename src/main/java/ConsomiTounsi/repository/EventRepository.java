package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Claim;
import ConsomiTounsi.entities.Event;
import ConsomiTounsi.entities.Shelf;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
   // @Query(value = "DELETE * FROM event WHERE id_event = ?1",nativeQuery = true)
   // public void deleteeventbyid(long id);

    Optional<Event> findTopByOrderByIdEventDesc() ;


    @Query("SELECT e FROM Event e WHERE e.idEvent=:id")
    public void deleteeventbyid(@Param("id") long id);

    @Query("SELECT e FROM Event e ORDER BY e.raisedAmount_event ASC")
    List<Event> getEventsByLevelorder();


    @Query("SELECT e FROM Event e ORDER BY e.raisedAmount_event DESC")
    List<Event> getEventsByLevelorder2();

    @Query(value = "SELECT clients_id_user FROM event_clients u WHERE u.events_id_event= :id " , nativeQuery = true)
    List<Long> ListP(@Param("id") long id);






}
