package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Delivery;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery,Long> {
	//@Query("SELECT d.scoreDelivery FROM Delivery d WHERE d.deliverer_id_User= :deliverer_id")
	//List<String> getScorebyDelivererI(@Param("deliverer_id") long deliverer_id);
	
	@Query(value = "SELECT d.score_delivery FROM delivery d WHERE d.deliverer_id_user =:id " , nativeQuery =
			true)
			List<Integer> getScorebyDelivererI(@Param("id") long id);
}

