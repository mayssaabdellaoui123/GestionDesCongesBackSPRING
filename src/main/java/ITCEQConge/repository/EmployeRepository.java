package ITCEQConge.repository;

import javax.transaction.Transactional;

import ITCEQConge.entities.Employe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface EmployeRepository extends CrudRepository<Employe, Long> {

	boolean existsByMatricule(String matricule);




	Optional<Employe> findTopByOrderByIdUserDesc();


	//Optional<Employe> findClientByMatricule(String Matricule);

	@Query("SELECT c FROM Employe c WHERE c.matricule = :m ")
	Employe findClientByMatricule(String m) ;


	@Query("SELECT COUNT(c) FROM Employe c")
	long getNombreClient();

	@Query("SELECT Count(c) FROM Employe c WHERE c.subMonth=:Month")
	long getClientsbysubmonth(@Param("Month") String Month);

	Employe findByIdUser(long iduser);



	Employe findByUsernameUser(String username);

	@Query("SELECT c FROM Employe c WHERE c.matricule= :matricule")
	Employe getClientByMatricule(@Param("matricule") String matricule) ;




	@Query("SELECT matricule FROM Employe c WHERE c.usernameUser = :username ")
	String findMatriculeByUserName(@Param("username")String username);

	@Query("SELECT Remplaceur FROM Employe c WHERE c.usernameUser = :username ")
	Boolean findRemplaceurByUserName(@Param("username")String username);

	@Query("SELECT c.idUser FROM Employe c WHERE c.Remplaceur= :remp")
	List<Long> getRemplaceurIdUser(@Param("remp")Boolean remp);









}
