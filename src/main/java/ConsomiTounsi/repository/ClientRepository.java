package ConsomiTounsi.repository;

import javax.transaction.Transactional;

import ConsomiTounsi.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ConsomiTounsi.entities.Client;

import java.util.List;

@Transactional
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
	
	@Query("SELECT COUNT(c) FROM Client c")
	long getNombreClient();

	@Query("SELECT Count(c) FROM Client c WHERE c.subMonth=:Month")
	long getClientsbysubmonth(@Param("Month") String Month);

;

}
