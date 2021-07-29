package ConsomiTounsi.repository;

import ConsomiTounsi.entities.JoursFeries;
import ConsomiTounsi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoursFeriesRepository extends JpaRepository<JoursFeries,Long> {
}
