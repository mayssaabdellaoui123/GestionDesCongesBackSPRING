package ConsomiTounsi.Controller.ClaimAndAd;




import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import ConsomiTounsi.Service.ClaimManagerInterface;
import ConsomiTounsi.entities.Claim;
import ConsomiTounsi.entities.ClaimType;


@RestController
@RequestMapping("/Claim")
@CrossOrigin(origins = "*")
public class ClaimController {
	
	@Autowired
	ClaimManagerInterface claimManagerI ;
	
	@GetMapping("/home")
	public String Home(){
		return ("WELCOME TO THE CLAIMANT SERVICES");
	}
	
	@GetMapping("/findClaim/{id}")
	public Claim FindClaim(@PathVariable("id") Long id){
		return claimManagerI.FindClaim(id);
	
	}
	
	@GetMapping("/find/{subject}")
	public List<Claim> FindBySubject(@PathVariable("subject") ClaimType subject){
		return claimManagerI.FindBySubject(subject);
	}
	
	
	@GetMapping("/find2/{status}")
	public List<Claim> FindByStatus(@PathVariable("status") String status){
		return claimManagerI.FindByStatus(status);
	}
	
	
	
	@GetMapping("/calc")
	public Long Calcul(){
		return claimManagerI.CountTotalClaims();
	}
	
	

	
	
	@GetMapping("/Bp/{Bp}")
	public int claimsNumber(@PathVariable("Bp") Long Bp){
		return claimManagerI.claimsNumber(Bp);
	}
	
	
	
	@GetMapping("/levelAsc")
	public List<Claim> GetClaimsByLevelorder() {
		return claimManagerI.GetClaimsByLevelorder();
	
	}
	
	
	@GetMapping("/levelDesc")
	public List<Claim> GetClaimsByLevelorder2() {
		return claimManagerI.GetClaimsByLevelorder2();
	
	}
	
	@GetMapping("/getC")
	public List<Claim> getClaims() {
	List<Claim> list = claimManagerI.retrieveAllClaim();
	return list;
	}
	
	@DeleteMapping("/remove/{id}")
	public List<Claim>  removeClaim(@PathVariable("id") Long id) {
	claimManagerI.deleteClaim(id);
	return claimManagerI.retrieveAllClaim();
	}
	
	@PostMapping("/add")
	public Claim addClaim(@RequestBody Claim C){
		 
		return claimManagerI.addClaim(C) ;	}
	
	@PutMapping("/modify")
	public Claim modifyClaim(@RequestBody Claim C) {
	return claimManagerI.updateClaim(C);
	}
	
	
	
	@PutMapping("/decision")
	public ResponseEntity<?> modifyDecisionAndStatus(@RequestBody Claim C) {
		claimManagerI.updateClaimDecision(C);
		return new ResponseEntity<>("your decision has been update and the status has been set",HttpStatus.OK);
	}
	
	/*@GetMapping("/mail")
	public ResponseEntity<?> mail(){
		 claimManagerI.SendEmail();
		 return new ResponseEntity<>("your decision has been update and the status has been set",HttpStatus.OK);
	}*/
	
	
	
	/*@PutMapping("/updateStatus/{id}")
	public Claim updatestatus(@PathVariable("id") Long id) {
	return claimManagerI.updateStatus(id);
	}*/
	
	
	
	
	/*"idAdvertisment" : 45 ,
    "dateAdvertisment" : 444554,
	"enddate" : 55554677 ,
	"finalviews": null,
	"targetviews" : null ,
    "typeAdvertisement" : null ,
	"cost" : null ,
	"channel" : null*/
	
	
	
	
	
	
	/*"idClaim" :11 ,
    "decision" : "merci pour votre temps, votre livraison va etre verifiée" ,
    "date" : 12 ,
    "status" : null, 
    "description" : " medium product i'm so disappointed, also bad delivery   ",
    "subject" : SYSTEM,
    "level" : null,
    "systemProb" : SERVER ,
    "deliveryProb" : null ,
    "ProductProb": null,
    "ProductWithProb" : 1 ,
    "user" : {
          "idUser" : 1,
"username_user" : null ,
"password_user" : null,
"phoneNumber_user" : null,
"emailAddress_user" : null,
"dateBirth_user" : null,
"address_user" : null,
"genderClient" : "FEMALE",
"workfieldClient" : null

    }
    */
	
	
	
	
	
	
}
