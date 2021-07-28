package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {

    @Query(value= "SELECT * FROM comment c WHERE c.most_pertinent_comment= TRUE AND c.subject_id_subject=:id ",nativeQuery= true)
    List<Comment> retrievePertinentCommentsBySubject(@Param("id") long id);

    @Modifying
    @Query(value="UPDATE comment c SET c.likes_comment=:nb WHERE c.id_comment=:id",nativeQuery= true)
    int addLike(@Param("nb") int nb , @Param("id") long id);

    @Modifying
    @Query(value="UPDATE comment c SET c.most_pertinent_comment = TRUE WHERE c.id_comment=:id",nativeQuery= true)
    int setPertinentComment(@Param("id") long id);

    @Modifying
    @Query(value="UPDATE comment c SET c.most_pertinent_comment = FALSE WHERE c.id_comment=:id",nativeQuery= true)
    int notPertinentComment(@Param("id") long id);

    @Modifying
    @Query(value="UPDATE comment c SET c.likes_comment=:nb WHERE c.id_comment=:id",nativeQuery= true)
    int dislike(@Param("nb") int nb , @Param("id") long id);
}
