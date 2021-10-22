package ITCEQConge.Service;


import ITCEQConge.entities.Deliverer;

import java.util.List;
import java.util.Optional;

public interface DelivererManagerInterface {
	
	List<Deliverer> retrieveAllDeliverer();
    Deliverer addDeliverer(Deliverer D);
    void DelivererOfTheMonthMail();
    void deleteDelivererById(Long id);
    void deleteDelivererById(String id);
    Deliverer updateDeliverer(Deliverer D);
    Deliverer FindDelivererById(Long id);
    Deliverer FindDelivererById(String id);
    long getNbAvailableDeliveres();
	long getNbDeliverer();
	Deliverer getDelivererOfTheMonth();
    double SommeSaliareDeliverer();
    void resetBonus();

    void salaireAvecPrime();
    double updateprimeDeliverer(long id);

    double calculatePrime(long id);

    void electDelivererOftheMonth();


	long FindMaxScore();
    Optional<Deliverer> FindDelivererByIdOpt(String id);
    Optional<Deliverer> FindDelivererByIdOpt(Long id);



}
