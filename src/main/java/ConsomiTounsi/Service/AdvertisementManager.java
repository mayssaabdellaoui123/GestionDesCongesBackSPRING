package ConsomiTounsi.Service;

import ConsomiTounsi.entities.*;

import ConsomiTounsi.repository.ProductRepository;
import ConsomiTounsi.repository.UserRepository;
import ConsomiTounsi.repository.AdvertisementRepository;
import ConsomiTounsi.repository.ClientRepository;
//import org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class AdvertisementManager implements AdvertisementManagerInterface{
  
	@Autowired
	AdvertisementRepository Adr;
	
	@Autowired
	 ProductRepository Pr;
	
	@Autowired
	 ClientRepository Clr;
	
	 
	@Autowired
	UserRepository Ur;

	@Autowired
	UserManager UMA ;
	
	 
	////////////////////////////Simple CRUD 
	
	
	
    @Override
    public List<Advertisement> retrieveAllAdvertisement() {
    	return (List<Advertisement>) Adr.findAll();
    }
   
    
    @Override
    public Advertisement addAdvertisement(Advertisement Ad)  {
	
       return Adr.save(Ad) ;
       
     }
    
    
    
    @Override
    public void deleteAdvertisement(Long id) {
    	 Adr.deleteById(id);
    }

    @Override
    public void deleteAdvertisement(String id) {
    	Adr.deleteById(Long.parseLong(id));
    }

    

    @Override
    public Advertisement FindAdvertisement(Long id) {
    	return Adr.findById(id).orElse(new Advertisement());
    }

    @Override
    public Optional<Advertisement> FindAdvertisement(String id) {
        return Adr.findById(Long.parseLong(id));
    }

	@Override
	public Advertisement updateAdvertisement(Advertisement Ad) {
	
         
		return Adr.save(Ad);
	}

	

	
	
	
	
	///////////////////////////////More Advanced ones 
	
	
	
	
	@Override
	public List<Product> showAdvertsementByCategory(Long id) {
		Advertisement theAd = Adr.findById(id).orElse(new Advertisement()) ;

		Client theclient = theAd.getClient() ;
		List<Product> F = Pr.findByCategoryProduct();
		List<Product> M = Pr.findByCategoryProduct2();
		List<Product> H = Pr.findByCategoryProduct5();
		List<Product> S = Pr.findByCategoryProduct4();
		List<Product> B = Pr.findByCategoryProduct6();
		
		List<Product> c = new ArrayList<>() ;
		
			 LocalDateTime date = LocalDateTime.now() ;
			 LocalDateTime BirthDate = theclient.getDateBirthUser().toInstant()
					 .atZone(ZoneId.systemDefault())
					 .toLocalDateTime(); ;
			 long age = ChronoUnit.YEARS.between(date, BirthDate);
			
            Gender genderClient = theclient.getGenderClient();
            WorkField workfieldClient = theclient.getWorkfieldClient();
            	if (age < 12) {c=B ;}
            	if ( genderClient == Gender.MALE) {
            		if (workfieldClient == WorkField.EDUCATION)
            	  {c = M; }
            		if (workfieldClient == WorkField.COSMETICS) {
            			{c= H ;}
            		}
            	}
            	if (genderClient==Gender.FEMALE)
            	{
            		if (workfieldClient == WorkField.EDUCATION)
              	  {c = F; }
              		if (workfieldClient == WorkField.COSMETICS) {
              			{c= S ;}
              		}
            	}
            		
		
		return c ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override	
	public String CountAdDays(Long id) {
	 Advertisement TargetedAd = Adr.findById(id).orElse(new Advertisement()) ;
	 LocalDateTime endDate = TargetedAd.getEnddate() ;
	 LocalDateTime date = LocalDateTime.now() ;
	 long period = ChronoUnit.MINUTES.between(date, endDate) ;
	 Long TheId = TargetedAd.getIdAdvertisment();
	 
	 return " The advertisement that has the id  " +TheId+ " has :" + " \n" +period+ " minutes left " ; 
		
		
   	 
   }


	@Override
	public String DeleteIfEnded(Long id) {
		Advertisement TargetedAd = Adr.findById(id).orElse(new Advertisement()) ;
		LocalDateTime endDate = TargetedAd.getEnddate() ;
		LocalDateTime date = LocalDateTime.now() ;
		 long period = ChronoUnit.SECONDS.between(date, endDate) ;
		 if ( period <=0 )  { Adr.deleteById(id) ; 
		  return "The Advertisement was deleted succesfully " ; }
		 else
			 return " The Advertisement still available" ;
				 
	
	
		 
		
		 
		
		
	}


	@Override
	public Long frontAd(String username){
    	User target=UMA.findUserByUsername(username) ;
    	Long id = target.getIdUser();
    	return id;

	}
}
	
	
		
		
	
	/*public Long Target(Authentication auth){
		String username = auth.getName();
		User user = Ur.findByUsername_user(username) ;
		Long theId = user.getIdUser();
		
		return theId ;
			
	
} */

	
	
	
	
	// This function uses the id of the connected client
	
	
		/*@Override
		public List<Product> showAdvertsementByCategory2(Authentication auth) {
			Client theclient = UMA.getConnectedUser(Authentication auth);
			List<Product> F = Pr.findByCategoryProduct();
			List<Product> M = Pr.findByCategoryProduct2();
			List<Product> H = Pr.findByCategoryProduct5();
			List<Product> S = Pr.findByCategoryProduct4();
			List<Product> B = Pr.findByCategoryProduct6();
			
			List<Product> c = new ArrayList<>() ;
			
				 LocalDateTime date = LocalDateTime.now() ;
				 LocalDateTime BirthDate = theclient.getDateBirthUser().toInstant()
						 .atZone(ZoneId.systemDefault())
						 .toLocalDateTime();
				 long age = ChronoUnit.YEARS.between(date, BirthDate);
				
	            Gender genderClient = theclient.getGenderClient();
	            WorkField workfieldClient = theclient.getWorkfieldClient();
	            	if (age < 12) {c=B ;}
	            	if ( genderClient == Gender.MALE) {
	            		if (workfieldClient == WorkField.EDUCATION)
	            	  {c = M; }
	            		if (workfieldClient == WorkField.COSMETICS) {
	            			{c= H ;}
	            		}
	            	}
	            	if (genderClient==Gender.FEMALE)
	            	{
	            		if (workfieldClient == WorkField.EDUCATION)
	              	  {c = F; }
	              		if (workfieldClient == WorkField.COSMETICS) {
	              			{c= S ;}
	              		}
	            	}
	            		
			
			return c ;
		}*/


	

	

	
