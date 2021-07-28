package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Advertisement;
import ConsomiTounsi.entities.Product;
import ConsomiTounsi.entities.TypeCategory;
import ConsomiTounsi.entities.TypeCriteria;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.persistence.Query;

//import org.springframework.security.core.Authentication;

public interface AdvertisementManagerInterface {
    List<Advertisement> retrieveAllAdvertisement();
    Advertisement addAdvertisement(Advertisement Ad) ;
    void deleteAdvertisement(Long id);
    void deleteAdvertisement(String id);
    
    Advertisement updateAdvertisement(Advertisement Ad);
    Advertisement FindAdvertisement(Long id);
    Optional<Advertisement> FindAdvertisement(String id);
    //List<Product>findByCategoryProduct(TypeCategory cp);
    //List<Product> showAdvertsementForAll(TypeCriteria criteria);
    List<Product> showAdvertsementByCategory( Long id);
    String CountAdDays(Long id) ;
    String DeleteIfEnded(Long id) ;
    Long frontAd(String username) ;
   //List<Product> showAdvertsementByCategory2(Authentication auth) ;
}
