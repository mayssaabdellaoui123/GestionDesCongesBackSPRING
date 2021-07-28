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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ConsomiTounsi.Service.DeliveryManagerInterface;
import ConsomiTounsi.entities.Delivery;
import ConsomiTounsi.entities.Product;
import ConsomiTounsi.repository.DeliveryRepository;

@RestController
@RequestMapping("/Delivery")
public class DeliveryController {
	@Autowired
	DeliveryManagerInterface DevliveryI ;
	
	@Autowired
	DeliveryRepository DI ;
	
	@GetMapping("/firstage")
	public String First(){
		return ("<h1> First Delivery </h1>");
		}
	@GetMapping("/find/{id}")
	public Optional<Delivery> getDevlivery(@PathVariable("id") long id){
		return DevliveryI .FindDelivery(id);
	}
	
	@GetMapping("/get")
	public List<Delivery> getDelivery() {
	List<Delivery> list = DevliveryI.retrieveAllDelivery();
	return list;
	}
	
	@DeleteMapping("/remove/{id}")
	public void removeDelivery(@PathVariable("id") long id) {
		DevliveryI .deleteDelivery(id);
	}
	
	@PostMapping("/add")
	public Delivery addDelivery(@RequestBody Delivery De){
		 
		return DevliveryI.addDelivery(De) ;	}
	
	@PutMapping("/modify")
    ResponseEntity<?> modifyDelivery(@RequestBody Delivery De) {

		DevliveryI.updateDelivery(De);
        return new ResponseEntity<>(new MessageResponseModel("Delivery Updated"), HttpStatus.CREATED);
    }

	/*@PutMapping("/modify")
	public Delivery modifyDelivery(@RequestBody Delivery De) {
	return DevliveryI.updateDelivery(De);
	}*/
	
	@GetMapping("/Score")
	public int CalculateScores(@RequestBody Delivery De){
		return DevliveryI.CalculateScore(De);
	}
	@PutMapping(value="/DelivererToDelivery/{livreurId}/{livraisonId}")
	public void affecterDeliveryDeliverer(@PathVariable("livreurId") int livreurId, @PathVariable("livraisonId") int livraisonId){
		DevliveryI.AffectLivreurLivraison(livreurId, livraisonId);
	}
	/*@PutMapping("/DelivererToDelivery")
    public void AffectDeliverer(@RequestParam("livreurId") long livreurId, @RequestParam("livraisonId") long livraisonId) {
		DevliveryI.AffectLivreurLivraison(livreurId, livraisonId);
    }*/
	
	@GetMapping(value= "/GetScore/{deliverer_id}")
	
	public List<Integer> getDeliveryScorebyDelivererID(@PathVariable("deliverer_id")long deliverer_id){
		
		return DI.getScorebyDelivererI(deliverer_id);
		
	}
	
	@GetMapping(value="/DelivererScore/{livreurId}")
	public int setScoreToDeliverer(@PathVariable("livreurId") long livreurId ){
		return DevliveryI.calculateScoreDeliverer(livreurId);
		
	}
	@PutMapping(value="/DistanceBetweenTwoPeople/{delvID}/{ClientId}/{livraisonId}")
	public double getDistance(@PathVariable("delvID") long delvID, @PathVariable("ClientId") long ClientId,@PathVariable("livraisonId") long livraisonId){
		return DevliveryI.distance(delvID,ClientId,livraisonId);
	}
	
	@PutMapping(value="/sffectLivreur/{client_id}/{livraisonId}")
	public void AffectNEARdeliverer(@PathVariable("client_id") long client_id, @PathVariable("livraisonId") long livraisonId){
		DevliveryI.AffectNEARdeliverer(client_id, livraisonId);
	
	}
	@PutMapping(value="/frais/{ordreid}")
	public double fraislivraison(@PathVariable("ordreid") long ordreid){
		return DevliveryI.FraisLivraison(ordreid);
	
	}
	
}