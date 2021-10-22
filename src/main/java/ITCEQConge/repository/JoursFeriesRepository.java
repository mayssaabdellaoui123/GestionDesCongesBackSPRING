package ITCEQConge.repository;

import ITCEQConge.entities.JoursFeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoursFeriesRepository extends JpaRepository<JoursFeries,Long> {
}
