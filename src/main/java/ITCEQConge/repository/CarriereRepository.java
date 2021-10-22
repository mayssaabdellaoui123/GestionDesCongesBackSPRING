package ITCEQConge.repository;

import ITCEQConge.entities.Carriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarriereRepository extends JpaRepository<Carriere,Long> {
}
