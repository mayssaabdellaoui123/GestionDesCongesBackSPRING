package ITCEQConge.controllers;

import java.util.List;
import java.util.Optional;

import ITCEQConge.Service.DelivererManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ITCEQConge.Service.DelivererManagerInterface;
import ITCEQConge.entities.Deliverer;

@RestController
@RequestMapping("/Deliverer")
public class DeliverersControllers {
	@Autowired
	DelivererManagerInterface DevlivererManI ;

	@Autowired
	DelivererManager manager;

	@GetMapping("/firstpage")
	public String First(){
		return ("<h1> First Delivery </h1>");
	}
	@GetMapping("/findD/{id}")
	public Optional<Deliverer> getDevliverer(@PathVariable("id") long id){
		return DevlivererManI.FindDelivererByIdOpt(id);
	}
	
	@GetMapping("/getD")
	public List<Deliverer> getDeliverer() {
	List<Deliverer> list = DevlivererManI.retrieveAllDeliverer();
	return list;
	}
	
	@DeleteMapping("/remove/{id}")
	public void removeDeliverer(@PathVariable("id") long id) {
		DevlivererManI.deleteDelivererById(id);
	}
	
	@PostMapping("/addDev")
	public Deliverer addDeliverer(@RequestBody Deliverer D){
		 
		return DevlivererManI.addDeliverer(D) ;	}

	@GetMapping("/MaxScore")
	public long getMaxOfScore() {
		return DevlivererManI.FindMaxScore();
	}
	@PutMapping("/Email")
	public  void EmailDelivererOfTheMonth(){
		manager.DelivererOfTheMonthMail();
	}
}
