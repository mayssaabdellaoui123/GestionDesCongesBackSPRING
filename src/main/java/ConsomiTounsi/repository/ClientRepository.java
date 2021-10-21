package ConsomiTounsi.repository;

import javax.transaction.Transactional;

import ConsomiTounsi.entities.Role;
import ConsomiTounsi.entities.User;
import ConsomiTounsi.entities.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ConsomiTounsi.entities.Client;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

	boolean existsByMatricule(String matricule);




	Optional<Client> findTopByOrderByIdUserDesc();


	//Optional<Client> findClientByMatricule(String Matricule);

	@Query("SELECT c FROM Client c WHERE c.matricule = :m ")
	Client findClientByMatricule(String m) ;


	@Query("SELECT COUNT(c) FROM Client c")
	long getNombreClient();

	@Query("SELECT Count(c) FROM Client c WHERE c.subMonth=:Month")
	long getClientsbysubmonth(@Param("Month") String Month);

	Client findByIdUser(long iduser);



	Client findByUsernameUser(String username);

	@Query("SELECT c FROM Client c WHERE c.matricule= :matricule")
	Client getClientByMatricule(@Param("matricule") String matricule) ;




	@Query("SELECT matricule FROM Client c WHERE c.usernameUser = :username ")
	String findMatriculeByUserName(@Param("username")String username);

	@Query("SELECT Remplaceur FROM Client c WHERE c.usernameUser = :username ")
	Boolean findRemplaceurByUserName(@Param("username")String username);

	@Query("SELECT c.idUser FROM Client c WHERE c.Remplaceur= :remp")
	List<Long> getRemplaceurIdUser(@Param("remp")Boolean remp);









}
