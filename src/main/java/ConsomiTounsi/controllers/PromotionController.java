package ConsomiTounsi.controllers;

import java.util.List;
import java.util.Optional;

import ConsomiTounsi.controllers.mouadh_Controllers.MessageResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ConsomiTounsi.Service.PromotionManagerInterface;
import ConsomiTounsi.entities.Promotion;
import ConsomiTounsi.repository.PromotionRepository;

@RestController
@RequestMapping("/promotion")
public class PromotionController {
	@Autowired
	PromotionManagerInterface Prm ;
	
	@Autowired
	PromotionRepository Pr ;
	
	@GetMapping("/firstage")
	public String First(){
		return ("<h1> First Promotion </h1>");
		}
	@GetMapping("/find/{id}")
	public Optional<Promotion> getPromotion(@PathVariable("id") long id){
		return Prm.FindPromotion(id);
	}
	
	@GetMapping("/get")
	public List<Promotion> getPromotion() {
	List<Promotion> list = Prm.retrieveAllPromotion();
	return list;
	}
	
	@DeleteMapping("/remove/{id}")
	public void removePromotion(@PathVariable("id") long id) {
           Prm .deletePromotion(id);
	}
	
	@PostMapping("/add")
	public Promotion addPromotion(@RequestBody Promotion Pi){
		 
		return Prm.addPromotion(Pi) ;	}
	
	@PutMapping("/modify")
    ResponseEntity<?> modifyDelivery(@RequestBody Promotion Pi) {

		Prm.updatePromotion(Pi);
        return new ResponseEntity<>(new MessageResponseModel("Promotion Updated"), HttpStatus.CREATED);
    }
	@PutMapping("/Email/{pid}")
	public  void EmailPromotion(@PathVariable("pid") long pid){
		Prm.PromotionNotificationandPriceReduction(pid);
	}

}
