package ITCEQConge.controllers.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ITCEQConge.Service.EmployeManagerInterface;
import ITCEQConge.entities.Employe;

@RestController
@RequestMapping("/admin/accounts/client")
public class AdminClientController {

	@Autowired
	EmployeManagerInterface clientS;

	@GetMapping("/retrieve-all")
	public List<Employe> getListClients(){
	return clientS.retrieveAllClient(); }
	
	@GetMapping("/retrieve-id")
	public Employe getClientById(@RequestParam("id") long id){
		return clientS.FindClientById(id);
	}
	
	@DeleteMapping("remove-id")
	public void removeClientByID(@RequestParam("id")long id){
		clientS.deleteClientById(id);
	}
	
	@PutMapping("/update")
	public Employe updateClient(@RequestBody Employe a){
		return clientS.updateClient(a);
	}
	
}
