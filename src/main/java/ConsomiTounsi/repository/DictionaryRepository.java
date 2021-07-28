package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Dictionary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface DictionaryRepository extends CrudRepository<Dictionary,Long> {

    @Query("SELECT d.badWord FROM Dictionary d")
    List<String> retrieveBadWords();

}
