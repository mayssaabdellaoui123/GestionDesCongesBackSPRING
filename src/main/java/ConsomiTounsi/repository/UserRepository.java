package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Role;
import ConsomiTounsi.entities.User;


import java.util.List;
import java.util.Optional;

import ConsomiTounsi.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional//(readOnly = true)

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	boolean existsByUsernameUser(String username);
	boolean existsByEmailAddressUser(String email);
	boolean existsByMatricule(String matricule);


	Optional<User>  findByUsernameUser(String username);

	Optional<User> findUserByMatricule(String matricule);

	List<User> findByFirstNameUser(String firstname);
	List<User> findByLastNameUser(String lastname);
	User findByIdUser(long iduser);

	@Modifying
	@Query("Update User u SET u.nbaccessUser=:nb WHERE u.usernameUser= :fn")
	int updateNbaccess(@Param("nb") int nb , @Param("fn") String fn );

	@Query("SELECT u FROM User u WHERE u.firstNameUser= :fn AND u.lastNameUser = :ln" )
	List<User> RetiveUserByFirstAndLastNameJPQL(@Param("fn") String fn , @Param("ln") String ln);


	@Query("SELECT u FROM User u WHERE u.roleUser = :r ")
	List<User>  findbyRole( UserRole r ) ;

	@Query("SELECT u.idUser FROM User u WHERE u.usernameUser= : user")
	long retrieveIdClientByUsername(@Param("user") String user);

	@Query("SELECT u.matricule FROM User u WHERE u.usernameUser= :usernameUser")
	String retrieveMatriculeClientByUsername(@Param("usernameUser") String usernameUser);


	@Query(value= " SELECT * , 0 AS clazz_ FROM user u WHERE u.departement_id_departement= :depId", nativeQuery = true)
	List<User>  getUserByIdDep( @Param("depId") Long depId ) ;

	@Query("SELECT u.idUser FROM User u WHERE u.matricule= :matricule")
	List<Long> getIdUserByMatricule(@Param("matricule") String matricule) ;
	
}
