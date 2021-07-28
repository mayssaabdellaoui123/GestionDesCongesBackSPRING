package ConsomiTounsi.controllers.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ConsomiTounsi.Service.ClientManagerInterface;
import ConsomiTounsi.entities.Client;

@RestController
@RequestMapping("/admin/accounts/client")
public class AdminClientController {

	@Autowired
	ClientManagerInterface clientS;

	@GetMapping("/retrieve-all")
	public List<Client> getListClients(){
	return clientS.retrieveAllClient(); }
	
	@GetMapping("/retrieve-id")
	public Client getClientById(@RequestParam("id") long id){
		return clientS.FindClientById(id);
	}
	
	@DeleteMapping("remove-id")
	public void removeClientByID(@RequestParam("id")long id){
		clientS.deleteClientById(id);
	}
	
	@PutMapping("/update")
	public Client updateClient(@RequestBody Client a){
		return clientS.updateClient(a);
	}
	
}
