package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Conge;
import ConsomiTounsi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {


    /*
    @Query(value= " SELECT * , 0 AS clazz_ FROM conge c WHERE c.users_id_user= :userId AND validation_primaire= :VF", nativeQuery = true)
    List<Conge> getCongeByUserIdUserAndVF(@Param("userId") Long userId, @Param("VF") Boolean VF ) ;
     */

    @Query(value= " SELECT * , 0 AS clazz_ FROM conge c WHERE c.users_id_user= :userId", nativeQuery = true)
    List<Conge> getCongeByUserIdUser(@Param("userId") Long userId ) ;


    @Query(value= " SELECT users_id_user , 0 AS clazz_ FROM conge c WHERE c.id_conge= :idConge", nativeQuery = true)
      Long getIdUserByIdConge(@Param("idConge") Long idConge ) ;

    @Query("SELECT c FROM Conge c WHERE c.validationPrimaire = :validationPrimaire ")
    List<Conge> GetCongesForSA(@Param("validationPrimaire") Boolean validationPrimaire);

    Conge getByIdConge(Long idConge);



}
