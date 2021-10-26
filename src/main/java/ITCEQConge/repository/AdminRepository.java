package ITCEQConge.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ITCEQConge.entities.Admin;
import ITCEQConge.entities.Role;

@Transactional
@Repository
public interface AdminRepository extends CrudRepository<Admin,Long>{

	List<Admin> findByRoleAdmin(Role roleAdmin);

	Admin findByUsernameUser(String username);

	@Query("SELECT roleAdmin FROM Admin a WHERE a.idUser = :id ")
	Role findRoleById(@Param("id") Long id);



	Admin findByMatriculeBoss(String matricule);

	@Query("SELECT matriculeBoss FROM Admin a WHERE a.usernameUser = :username ")
	String findMatriculeBossByUserName(@Param("username")String username);

	@Query("SELECT a FROM Admin a WHERE a.idUser = :idUser ")
	Admin findAdminByIduser(@Param("idUser") Long idUser);




	//Admin findByUsernameUser(String name);

	@Query("SELECT COUNT(a) FROM Admin a WHERE a.roleAdmin= :role" )
	long getNbAdminByRole(@Param("role") Role role);
	
	@Query("SELECT COUNT(a) FROM Admin a")
	long getNbAdmin();

	@Modifying
	@Query(value="UPDATE admin a SET a.nbabsence_admin=:nb" ,nativeQuery= true )
	int resetAbsence(@Param("nb") int nb);

	@Modifying
	@Query(value="UPDATE admin a SET a.password_user=:pwd where  a.id_user=:id" ,nativeQuery= true )
	int updatePassword(@Param("pwd") String pwd , @Param("id") long id);

	@Modifying
	@Query(value="UPDATE admin a SET a.nbabsence_admin=:nb WHERE a.id_user=:id",nativeQuery= true)
	int AddAbsence(@Param("nb") int nb , @Param("id") long id);

	@Query("SELECT a FROM Admin a WHERE a.matriculeBoss= :matricule")
	Admin getAdminByMatricule(@Param("matricule") String matricule) ;

	

}