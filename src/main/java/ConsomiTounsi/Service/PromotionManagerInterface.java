package ConsomiTounsi.Service;

import java.util.List;
import java.util.Optional;

import ConsomiTounsi.entities.Promotion;

public interface PromotionManagerInterface {
	List<Promotion> retrieveAllPromotion();
	Promotion addPromotion(Promotion Pm);
    void deletePromotion(Long id);
    void deletePromotion(String id);
    Optional<Promotion> FindPromotion(Long id);
    Optional<Promotion> FindPromotion(String id);
    Promotion updatePromotion(Promotion Pm);
	void PromotionNotificationandPriceReduction(long pid);

}
