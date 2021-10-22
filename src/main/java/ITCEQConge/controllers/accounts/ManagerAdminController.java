package ITCEQConge.controllers.accounts;

import java.util.List;

import ITCEQConge.controllers.simple_controllers.MessageResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ITCEQConge.Service.AdminManagerInterface;
import ITCEQConge.entities.Admin;
import ITCEQConge.entities.Role;

@RestController
@RequestMapping("/manager/accounts/admin")
public class ManagerAdminController {
	
	@Autowired
	AdminManagerInterface adminS;



	@PostMapping("/add")
	public ResponseEntity register(@RequestBody Admin user){


		adminS.AddAdmin(user);
		return new ResponseEntity<>(new MessageResponseModel("Admin registered successfully!"), HttpStatus.OK);
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
