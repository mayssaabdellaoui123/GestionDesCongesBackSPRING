package ITCEQConge.controllers.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ITCEQConge.Service.DelivererManagerInterface;
import ITCEQConge.entities.Deliverer;

@RestController
@RequestMapping("/admin/accounts/deliverer")
public class AdminDelivererController {

	@Autowired
	DelivererManagerInterface delivererS;

	@PostMapping("/add")
	public Deliverer addDeliverer(@RequestBody Deliverer a){
		Deliverer deliverer = delivererS.addDeliverer(a);
		return deliverer ;	}
	
	@GetMapping("/retrieve-all")
	public List<Deliverer> getListDeliverers(){
	return delivererS.retrieveAllDeliverer(); }
	
	@GetMapping("/retrieve-id")
	public Deliverer getDelivererById(@RequestParam("id") long id){
		return delivererS.FindDelivererById(id);
	}
	
	@DeleteMapping("/remove-id")
	public void removeClientByID(@RequestParam("id")long id){
		delivererS.deleteDelivererById(id);
	}

}
