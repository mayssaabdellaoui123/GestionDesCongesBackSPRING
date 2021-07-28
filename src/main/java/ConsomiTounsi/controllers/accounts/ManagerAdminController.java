package ConsomiTounsi.controllers.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ConsomiTounsi.Service.AdminManagerInterface;
import ConsomiTounsi.entities.Admin;
import ConsomiTounsi.entities.Role;

@RestController
@RequestMapping("/manager/accounts/admin")
public class ManagerAdminController {
	
	@Autowired
	AdminManagerInterface adminS; 

	@PostMapping("/add")
	public Admin register(@RequestBody Admin user){
		return adminS.AddAdmin(user);
	}

	@GetMapping("/retrieve-all")
	public List<Admin> getListAdmins(){
	return adminS.retrieveAllAdmin(); }
	
	@GetMapping("/retrieve-id")
	public Admin getAdminById(@RequestParam("id") long id){
		return adminS.FindAdminById(id);
	}
	
	@DeleteMapping("remove-id")
	public void removeAdminByID(@RequestParam("id")long id){
		adminS.deleteAdminById(id);
	}
	
	@GetMapping("/retrieve-role")
	public List<Admin> getAdminsbyRole(@RequestParam("role") Role role ){ 
		return adminS.FindAdminByRole(role);}

}
