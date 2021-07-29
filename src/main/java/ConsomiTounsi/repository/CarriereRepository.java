package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Carriere;
import ConsomiTounsi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarriereRepository extends JpaRepository<Carriere,Long> {
}
