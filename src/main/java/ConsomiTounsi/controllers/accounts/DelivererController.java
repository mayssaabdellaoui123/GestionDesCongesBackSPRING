package ConsomiTounsi.controllers.accounts;

import ConsomiTounsi.Service.DelivererManagerInterface;
import ConsomiTounsi.entities.Deliverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliverer")
public class DelivererController {

    @Autowired
    DelivererManagerInterface delivererS;

    @PutMapping("/update")
    public void updateDeliverer(@RequestBody Deliverer a){
         delivererS.updateDeliverer(a);
    }

}

	
	/*{
    "idUser": 8,
    "firstNameUser": "sarra",
    "lastNameUser": "beddey",
    "usernameUser": null,
    "passwordUser": null,
    "phoneNumberUser": null,
    "emailAddressUser": null,
    "dateBirthUser": null,
    "addressUser": null,
    "delivererOfTheMonthDeliverer": false,
    "availableDeliverer": false,
    "distanceDeliverer": 0.0,
    "bonusDeliverer": 0.0}
	
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
	
	@DeleteMapping("remove-id")
	public void removeClientByID(@RequestParam("id")long id){
		delivererS.deleteDelivererById(id);
	}
	
	@PutMapping("/update")
	public Deliverer updateDeliverer(@RequestBody Deliverer a){
		return delivererS.updateDeliverer(a);
	}
}*/
