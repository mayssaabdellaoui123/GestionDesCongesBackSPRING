package ConsomiTounsi.Controller.ClaimAndAd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ConsomiTounsi.Service.ClientManagerInterface;
import ConsomiTounsi.entities.Advertisement;
import ConsomiTounsi.entities.Claim;
import ConsomiTounsi.entities.Client;

@RestController
@RequestMapping("/Client")
public class testClient {
	
	
	@Autowired 
	ClientManagerInterface CT ;
	
	
	
	@GetMapping("/home")
	public String Home(){
		return ("WELCOME TO THE Client Interface");
	}

	
	
	
	@PostMapping("/add")
	public Client addAd(@RequestBody Client Cl){
		 
		return CT.addClient(Cl) ;	}
	
	
	@GetMapping("/getC")
	public List<Client> retrieveAllClient() {
	List<Client> list = CT.retrieveAllClient();
	return list;
	}
	
	
	
	
	/*"idUser" : 5,
	"username_user" : null ,
	"password_user" : null,
	"phoneNumber_user" : null,
	"emailAddress_user" : null,
	"dateBirth_user" : null,
	"address_user" : null,
	"genderClient" : "FEMALE",
    "workfieldClient" : null
*/
}
