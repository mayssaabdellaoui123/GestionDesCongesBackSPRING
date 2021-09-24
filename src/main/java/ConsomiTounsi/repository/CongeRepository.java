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

    @Query("SELECT c FROM Conge c WHERE c.validationPrimaire= :fn AND u.validationFinale = :ln AND u.validationFinale = :ln" )
    List<User> RetiveUserByFirstAndLastNameJPQL(@Param("fn") String fn , @Param("ln") String ln);


}
