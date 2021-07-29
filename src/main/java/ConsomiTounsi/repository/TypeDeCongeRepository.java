package ConsomiTounsi.repository;

import ConsomiTounsi.entities.TypeDeConge;
import ConsomiTounsi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDeCongeRepository extends JpaRepository<TypeDeConge,Long> {
}
