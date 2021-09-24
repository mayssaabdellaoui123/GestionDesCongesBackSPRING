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

    @Query(value = "SELECT * FROM conge c WHERE c.validation_primaire= :validation  AND c.users_id_user= :id " , nativeQuery =
            true)
    List<Conge> retrieveCongeNonValidéByDepartment(@Param("validation") Boolean validation , @Param("id") long id);
}
