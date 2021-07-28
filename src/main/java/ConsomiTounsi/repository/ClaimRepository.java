package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Claim;
import ConsomiTounsi.entities.ClaimType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ClaimRepository extends CrudRepository<Claim,Long> {

	
	@Query("SELECT m FROM Claim m WHERE m.subject=:subject")
    List<Claim> findBysubject(@Param("subject") ClaimType subject);
	
	
	@Query("SELECT m FROM Claim m WHERE m.status=:status")
    List<Claim> findBystatus(@Param("status") String status);
	
	Optional<Claim> findByDate(Date date);
	
	//@Query("SELECT c FROM Claim c WHERE c.IdUser=:IdUser")
	//Optional<Claim> findByIdUser (Long idUser) ;
	
	
	@Query("SELECT COUNT(m) FROM Claim m")
	long getNumberOfClaims();

	
	@Query("SELECT m FROM Claim m ORDER BY m.Level ASC")
	List<Claim> getClaimsByLevelorder();
	
	
	@Query("SELECT m FROM Claim m ORDER BY m.Level DESC")
	List<Claim> getClaimsByLevelorder2();
	

}
