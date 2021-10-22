package ITCEQConge.controllers.accounts;

import ITCEQConge.Service.EmployeManager;
import ITCEQConge.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ITCEQConge.Service.AdminManagerInterface;
import ITCEQConge.Service.EmployeManagerInterface;
import ITCEQConge.Service.DelivererManagerInterface;
import ITCEQConge.entities.Role;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class UsersController {

	@Autowired
    EmployeManagerInterface clientS;
	
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

