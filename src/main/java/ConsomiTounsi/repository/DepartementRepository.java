package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement,Long> {

    boolean existsByMatriculeBoss(String matricule);

    @Query("SELECT d.nomDepartement FROM Departement d WHERE d.matriculeBoss=:matriculeBoss")
    List<String> getNameDepartmentByMatriculeBoss(@Param("matriculeBoss") String matriculeBoss);

    @Query("SELECT d.nomDepartement FROM Departement d WHERE d.matriculeBoss=:matriculeBoss")
    String getNameDepartmentByMatriculeBossUnique(@Param("matriculeBoss") String matriculeBoss);


    @Query("SELECT d.idDepartement FROM Departement d WHERE d.matriculeBoss=:matriculeBoss")
    List<Long> getIdDepartmentByMatriculeBoss(@Param("matriculeBoss") String matriculeBoss);

    @Query("SELECT d FROM Departement d WHERE d.matriculeBoss=:matriculeBoss")
    List<Departement> getDepartmentByMatriculeBoss(@Param("matriculeBoss") String matriculeBoss);




}
