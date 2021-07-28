package ConsomiTounsi.controllers.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ConsomiTounsi.Service.UserManagerInterface;
import ConsomiTounsi.entities.User;

@RestController
@RequestMapping("/admin/accounts")
public class AdminUserController {

	@Autowired
	UserManagerInterface userS;
	
	@GetMapping("/username")
	public User getUserByUsername(@RequestParam("username") String username){
		return userS.findUserByUsername(username);
	}
	
	@GetMapping("/lastandfirstName")
	public List<User> getUserByFirstAndLastName(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname){
		return userS.findUserByLastNameAndFirstName(firstname, lastname);
	}
	
	@GetMapping("/firstname")
	public List<User> getUserByFirstName(@RequestParam("firstname") String firstname){
		return userS.findUserByFirstName(firstname);
	}
	@GetMapping("/lastname")
	public List<User> getUserByLastName(@RequestParam("lastname") String lastname){
		return userS.findUserByLastName(lastname);
	}
	
}
