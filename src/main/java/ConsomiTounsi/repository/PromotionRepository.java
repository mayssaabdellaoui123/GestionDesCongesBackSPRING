package ConsomiTounsi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ConsomiTounsi.entities.Delivery;
import ConsomiTounsi.entities.Promotion;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion,Long>{

}
