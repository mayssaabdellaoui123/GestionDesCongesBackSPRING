package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Subject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SubjectRepository extends CrudRepository<Subject,Long> {

    @Modifying
    @Query(value="UPDATE subject s SET s.likes_subject=:nb WHERE s.id_subject=:id",nativeQuery= true)
    int addLike(@Param("nb") int nb , @Param("id") long id);

    @Modifying
    @Query(value="UPDATE subject s SET s.featured_subject = TRUE WHERE s.id_subject=:id",nativeQuery= true)
    int setFeautured(@Param("id") long id);

    @Modifying
    @Query(value="UPDATE subject s SET s.featured_subject = FALSE WHERE s.id_subject=:id",nativeQuery= true)
    int NotFeatured(@Param("id") long id);

    @Modifying
    @Query(value= "SELECT s FROM subject s WHERE s.featured_subject = TRUE",nativeQuery= true)
    List<Subject> getFeaturedSubjects();

}