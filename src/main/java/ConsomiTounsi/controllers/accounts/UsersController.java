package ConsomiTounsi.controllers.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ConsomiTounsi.Service.AdminManagerInterface;
import ConsomiTounsi.Service.ClientManagerInterface;
import ConsomiTounsi.Service.DelivererManagerInterface;
import ConsomiTounsi.entities.Deliverer;
import ConsomiTounsi.entities.Role;

@RestController
@RequestMapping("/dashboard")
public class UsersController {

	@Autowired
	ClientManagerInterface clientS;
	
	@Autowired
	AdminManagerInterface adminS;
	
	@Autowired
	DelivererManagerInterface delivererS;
	
	@GetMapping("/countClients")
	public long NbClients(){
		return clientS.getNombreClient();
	}
	
	@GetMapping("/countAdmins")
	public long NbAdmins(){
		return adminS.getNbAdmin();
	}
	
	@GetMapping("/countAdmins-role")
	public long NbAdminsByRole(@RequestParam("role")Role role){
		return adminS.getNbAdminByRole(role);
	}
	
	/*@GetMapping("/countsDeliverer")
	public long NbDeliverers(){
		return delivererS.getNbDeliverer();
	}
	
	@GetMapping("/countsAvailableDeliveres")
	public long NbAvailableDeliveres(){
		return delivererS.getNbAvailableDeliveres();
	}
	
	@GetMapping("/DelivererOfTheMonth")
	public Deliverer getDelivererOfTheMonth(){
		return delivererS.getDelivererOfTheMonth();
	}*/
}

