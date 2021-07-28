package ConsomiTounsi.Service;




import ConsomiTounsi.entities.Claim;

import ConsomiTounsi.entities.ClaimType;
import ConsomiTounsi.entities.DeliveryProb;
import ConsomiTounsi.entities.Product;
import ConsomiTounsi.entities.ProductProb;
import ConsomiTounsi.entities.SystemProb;
import ConsomiTounsi.repository.ClaimRepository;
import ConsomiTounsi.repository.ProductRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ClaimManager implements ClaimManagerInterface{
	
	@Autowired 
	ClaimRepository Cr;
	
	
	private JavaMailSender javaMailSender;
    @Autowired
    public ClaimManager(JavaMailSender javaMailSender)
    {
        this.javaMailSender=javaMailSender;
    }
	
	
	@Autowired 
	ProductRepository Pr ;
	
	
	@Autowired 
	ProductManager productManager ;
	///////////////// SIMPLE CRUD 

    @Override
    public List<Claim> retrieveAllClaim() {
    	
    	return (List<Claim>) Cr.findAll();
    }

    

    @Override
    public void deleteClaim(Long id) {
    	 Cr.deleteById(id);

    }

    @Override
    public void deleteClaim(String id) {
    	  Cr.deleteById(Long.parseLong(id));

    }

    @Override
    public Claim updateClaim(Claim C) {
    	
    	
    	
    	
        return Cr.save(C);
    }

    @Override
    public Claim FindClaim (Long id) {
        return Cr.findById(id).orElse(null);
    }

    @Override
    public Optional<Claim> FindClaim2(String id) {
        return   Cr.findById(Long.parseLong(id));
    }

	// counting number of claims for a given product
	@Override
	public int claimsNumber(Long Bp) {
		int Nb = 0;
		for (Claim claim : Cr.findAll()){
			if (claim.getProductWithProb()== Bp )
			{Nb +=1 ;}
		}
		return Nb;
	}

	// get products with 10 claims to block them
	@Override
	public List<Long> WorstProducts () {
		List<Product> elProducts = (List<Product>) Pr.findAll() ;
		List<Long> idfeedback = productManager.sendIdClaimedProduct() ;
		List<Long> Worst = new ArrayList<>();
		for (Long theId : idfeedback){
			int ClaimNumber = claimsNumber(theId) ;
			if ( ClaimNumber > 10 ) {
				Worst.add(theId); }

		} return Worst ;

	}
    
    
    ////////////////////// MORE ADVANCED ONES
    
    @Override
    public Claim addClaim(Claim C) {
    	
    	//Setting the level of disappointement
    	String Description = C.getDescription();
    	if (Description.toLowerCase().indexOf("angry",0) > 0 || Description.toLowerCase().indexOf("worst",0) > 0 || Description.toLowerCase().indexOf("urgent",0) > 0) 
    		{ C.setLevel(1); }
    	else if (Description.toLowerCase().indexOf("disappointed",0) > 0 || Description.toLowerCase().indexOf("medium",0) > 0 || Description.toLowerCase().indexOf("damaged",0) > 0) 
    		{ C.setLevel(2); }
    	else
    	    { C.setLevel(3); }
    		
    	// Setting the status to Not Treated by default
    	C.setStatus("Not_Treated");
    	
    	
    	// setting the date 
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        C.setDate(formatter.format(date)) ;
    	
    	
        return Cr.save(C);
    }
    
	@Override
	public List<Claim> FindBySubject(ClaimType subject) {
		
		return Cr.findBysubject(subject); 
	}
	
	
	
	@Override
	public List<Claim> FindByStatus(String status) {
		
		return Cr.findBystatus(status); 
	}

	@Override
	public Optional<Claim> FindByDate(Date date) {
		
		return Cr.findByDate(date); 
	}

	public List<Claim> GetClaimsByLevelorder() {
		return Cr.getClaimsByLevelorder() ;
	}
	
	
	public List<Claim> GetClaimsByLevelorder2() {
		return Cr.getClaimsByLevelorder2() ;
	}
	

	@Override
	public Claim updateClaimDecision(Claim C) {
		
		
		
		
		// checking the Claim Type 
		if (C.getSubject() == ClaimType.SYSTEM){
			if (C.getSystemProblem()== SystemProb.SERVER){
				C.setDecision("Hi " +C.getUser().getUsernameUser()+ " We are incredibly sorry. "
						+ "We may be facing some technical issues with our servers. We are working on it to provide the best services to our customers. Thank you for contacting us ");
			}
			if (C.getSystemProblem()== SystemProb.OPERATIONAL_MALFUNCTION){
				C.setDecision("Hi " +C.getUser().getUsernameUser()+ " We apologize for the operational malfunctions that u have faced. "
						+ "We will fix this abnormality as soon as possible, Thank you for contacting us" ) ;
		}
			if (C.getSystemProblem()== SystemProb.UNCORRECT_TIMING){
				C.setDecision("Hi " +C.getUser().getUsernameUser()+ "Thank you for reaching us."
						+ " Our team are working on renewing the expired certificate to fix the timing problem, we are sorry for the encountered issue, Thank you for contacting us" ) ;
		}
		}
		else 
		if (C.getSubject() == ClaimType.DELIVERY){
					
					if (C.getDeliveryProblem()== DeliveryProb.LATE_SHIPPING){
						C.setDecision("Hi " +C.getUser().getUsernameUser()+ " we are deeply sorry for this experience."
								+ "we are always trying to enhance our shipping service. You will be getting a compensation for the next product that you buy. Thank you for contacting us ");
					}
					
					if (C.getDeliveryProblem()== DeliveryProb.DAMAGED){
						C.setDecision("Hi " +C.getUser().getUsernameUser()+ "Please accept our apologies. we are always trying to enhance our shipping service. You will be getting a new product and a compensation for the next product that you buy. Thank you for contacting us ");
					}
				}
					
		else
		if (C.getSubject() == ClaimType.PRODUCT){
			
			
			if (C.getProductProblem()== ProductProb.QUALITY) {
				C.setDecision("Hi " +C.getUser().getUsernameUser()+ " We wanted to reach out and apologize."
						+ " We are always working to provide the best experience to our customers, that's why we are providing a quality control service and your feedback helps us upgrading it. Thank you for contacting us  ");
			}

			if (C.getProductProblem()== ProductProb.WRONG_PRODUCT){
				C.setDecision("Hi " +C.getUser().getUsernameUser()+ " We deeply regret this mistake. "
						+ "Your product will be replaced as soon as possible. we appreciate your understanding. Thank you for contacting us" ) ;
		}
			
		}
			
			
			
	
	
		
		// setting the status 
		String treatement2 = "Treated" ;
		String Des = C.getDecision(); 
		if (Des != null) {
			C.setStatus(treatement2); }
		
		
		// sending Email 
		String emailOfUser = C.getUser().getEmailAddressUser() ;
		this.sendEmail(emailOfUser,"Your claim got answered, please visit our website to check it ");
		return Cr.save(C);
	}

	
	@Override 
	public Long CountTotalClaims() {
		return Cr.getNumberOfClaims();
	}
	

	public void sendEmail(String sendTo,String text) throws MailException {
		
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setFrom("consommitounsi2@gmail.com");
        simpleMailMessage.setSubject("Claim Response");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
        
	}
	



}
