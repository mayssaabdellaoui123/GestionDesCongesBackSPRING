package ConsomiTounsi.repository;

import ConsomiTounsi.entities.User;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional//(readOnly = true)

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	boolean existsByUsernameUser(String username);
	boolean existsByEmailAddressUser(String email);

	Optional<User>  findByUsernameUser(String username);

	List<User> findByFirstNameUser(String firstname);
	List<User> findByLastNameUser(String lastname);
	List<User> findByIdUser(long iduser);

	@Modifying
	@Query("Update User u SET u.nbaccessUser=:nb WHERE u.usernameUser= :fn")
	int updateNbaccess(@Param("nb") int nb , @Param("fn") String fn );

	@Query("SELECT u FROM User u WHERE u.firstNameUser= :fn AND u.lastNameUser = :ln" )
	List<User> RetiveUserByFirstAndLastNameJPQL(@Param("fn") String fn , @Param("ln") String ln);


	//@Query("SELECT u FROM USER u WHERE u.username_user =: username ")
	//User findByUsername_user (@Param("username")String username ) ;

	@Query("SELECT u.idUser FROM User u WHERE u.usernameUser= : user")
	long retrieveIdClientByUsername(@Param("user") String user);

	
	
}
