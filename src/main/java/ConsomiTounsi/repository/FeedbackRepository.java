package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback,Long> {
    List<Feedback> findTop10ByNote(float note);
}
