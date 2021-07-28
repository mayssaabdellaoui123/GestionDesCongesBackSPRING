package ConsomiTounsi.Controller.ClaimAndAd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ConsomiTounsi.Service.ProductManagerInterface;
import ConsomiTounsi.entities.Advertisement;
import ConsomiTounsi.entities.Feedback;
import ConsomiTounsi.entities.Product;
import ConsomiTounsi.entities.TypeCategory;
import ConsomiTounsi.Service.ClaimManagerInterface;
import ConsomiTounsi.Service.FeedbackManagerInterface;
import ConsomiTounsi.Service.ProductManager;

@RestController
@RequestMapping("/Product")
public class testProduct {
	
	
	@Autowired
	ProductManagerInterface testProductI ;
	@Autowired
	FeedbackManagerInterface F ;
	
	@Autowired 
	ClaimManagerInterface C ;
	
	
	@GetMapping("/home")
	public String Home(){
		return ("WELCOME TO THE PRODUCT");
	}
 

	@PostMapping("/add")
	public Product addAd(@RequestBody Product P){
		 
		return testProductI.addProduct(P) ;	}
	
	@PostMapping("/addFeedback")
	public Feedback addFeedback(@RequestBody Feedback A){
		 
		return F.addFeedback(A) ;	}
	
	
	@GetMapping("/feedback")
	public List<Long> WorstProducts(){
		return C.WorstProducts(); }
	
	
	
	
	/*@GetMapping("/getCat/{cp}")
	public List<Product> findByCategoryProduct(@PathVariable("cp") TypeCategory cp) {
	List<Product> list = testProductI.findByCategoryProduct(cp);
	return list;
	}
	
	*/
}








/*"idProduct" : 8 ,
    "quantityProduct" : 45 ,
    "codeProduct" : null ,
    "ratingProduct" : 5 ,
    "nameProduct" : null ,
    "categoryProduct" : "makeUp" ,
    "priceProduct" : 46 ,
    "shelfProduct": null ,
	"availableProduct" : true ,
	"descriptionProduct" : null ,
	"weightProduct" : 54 
}*/







/*
"idAdvertisment" : 45 ,
"dateAdvertisment" : 444554,
"enddate" : 55554677 ,
"finalviews": null,
"targetviews" : null ,
"typeAdvertisement" : null ,
"cost" : null ,
"channel" : null ,
"client" : {
    
"idUser" : 5,
"username_user" : "testing" ,
"password_user" : null,
"phoneNumber_user" : null,
"emailAddress_user" : null,
"dateBirth_user" : null,
"address_user" : null,
"genderClient" : "FEMALE",
"workfieldClient" : null
}

*/


/*
"idProduct" : 8 ,
"quantityProduct" : 45 ,
"codeProduct" : null ,
"ratingProduct" : 5 ,
"nameProduct" : null ,
"categoryProduct" : "ShavingTools" ,
"priceProduct" : 46 ,
"shelfProduct": null ,
"availableProduct" : true ,
"descriptionProduct" : null ,
"weightProduct" : 54 ,
"client": { 
    "idUser" : 5,
"username_user" : null ,
"password_user" : null,
"phoneNumber_user" : null,
"emailAddress_user" : null,
"dateBirth_user" : null,
"address_user" : null,
"genderClient" : "FEMALE",
"workfieldClient" : null
}
*/