package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Delivery;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

public interface DeliveryManagerInterface {
    List<Delivery> retrieveAllDelivery();
    Delivery addDelivery(Delivery De);
    void deleteDelivery(Long id);
    void deleteDelivery(String id);
    Optional<Delivery> FindDelivery(Long id);
    Optional<Delivery> FindDelivery(String id);
    int CalculateScore(Delivery D);
	Delivery updateDelivery(Delivery De);
	void AffectLivreurLivraison(long livreurId, long livraisonId);
	List<Integer> getScorebyDelivererI(long deliverer_id);
	int calculateScoreDeliverer(long deliverer_id);
	double distance(long delvID, long ClientId,long livraisonId);
	void AffectNEARdeliverer(long client_id, long livraisonId);
	double FraisLivraison(long ordreid);

}
