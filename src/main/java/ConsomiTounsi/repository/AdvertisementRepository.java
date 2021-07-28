package ConsomiTounsi.repository;

import ConsomiTounsi.entities.Advertisement;

import ConsomiTounsi.entities.TypeAdvertisement;


import java.util.Date;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement,Long> {
	Optional<Advertisement> findByDateAdvertisment ( Date dateAdvertisment);
	Optional<Advertisement> findByTypeAdvertisement(TypeAdvertisement typeAdvertisement) ;
	Optional<Advertisement> findByEnddate(Date enddate) ;
	Optional<Advertisement> findByChannel(String channel) ;
	
	
	
	/*@Query("SELECT m FROM Claim m WHERE m.categoryProduct=:makeUp")
    List<Product> findByCategoryProduct(@Param("categoryProduct") TypeCategory makeUp);
    
    
    */
	
	/*@Query("SELECT p FROM Product p WHERE p.categoryProduct= :cp" )
	List<Product> findByCategoryProduct(@Param("cp") TypeCategory cp);
    */
}
