package ConsomiTounsi.repository;

import ConsomiTounsi.entities.TitreDeConge;
import ConsomiTounsi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitreDeCongeRepository extends JpaRepository<TitreDeConge,Long> {
}
