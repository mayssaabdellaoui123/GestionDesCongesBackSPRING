package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Deliverer;


import ConsomiTounsi.entities.Delivery;
import ConsomiTounsi.entities.DurationOfDelivering;
import ConsomiTounsi.entities.Order;
import ConsomiTounsi.entities.Promotion;
import ConsomiTounsi.entities.QualityOfDelivering;
import ConsomiTounsi.entities.ServiceDelivering;
import ConsomiTounsi.repository.ClientRepository;
import ConsomiTounsi.repository.DelivererRepository;
import ConsomiTounsi.repository.DeliveryRepository;
import ConsomiTounsi.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
@Service
public class DeliveryManager implements DeliveryManagerInterface{
	@Autowired 
	 DeliveryRepository Devr;
	@Autowired 
	 DelivererRepository fr;
	@Autowired 
	 ClientRepository cl;
	@Autowired 
	 OrderRepository Or;
	@Autowired 
	 DelivererManager dm;
	@Autowired 
	 DeliveryManager dr;
    @Override
    public List<Delivery> retrieveAllDelivery() {
    	return (List<Delivery>) Devr.findAll();
    }
    
    @Override
    public Delivery addDelivery(Delivery De) {
    	return Devr.save(De);
    }

    @Override
    public void deleteDelivery(Long id) {
    	Devr.deleteById(id);
    }

    @Override
    public void deleteDelivery(String id) {
    	Devr.deleteById(Long.parseLong(id));
    }

    @Override
    public Delivery updateDelivery(Delivery De) {
    	return Devr.save(De);
    }

    @Override
    public Optional<Delivery> FindDelivery(Long id) {
    	return Devr.findById(id);
    }

    @Override
    public Optional<Delivery> FindDelivery(String id) {
    	return Devr.findById(Long.parseLong(id));
    }
    //Calculate score delivery using quality, service and duration (formulaire)
    public int CalculateScore(Delivery D) {
    	
    	QualityOfDelivering Quality = D.getQuality_delivery();
    	ServiceDelivering Service = D.getService_delivery();
    	DurationOfDelivering Duration = D.getDuration_delivery();
    	int Score = D.getScoreDelivery();
    	Score=0; 
    	if ( Quality == QualityOfDelivering.good )
    		{Score=Score+1; }
    	System.out.println("Score:"+Score);

    	if ( Quality == QualityOfDelivering.great )
    	    {Score=Score+3; }
    	System.out.println("Score:"+Score);
    	 if  ( Quality == QualityOfDelivering.bad )
		{Score=Score-1; }
     	System.out.println("Score:"+Score);

    	if  ( Quality == QualityOfDelivering.awful)
		{Score=Score-2; }
    	System.out.println("Score:"+Score);

    	if  ( Service == ServiceDelivering.one)
		{Score=Score+1; }
    	System.out.println("Score:"+Score);

    	if  ( Service == ServiceDelivering.two)
		{Score=Score+2; }
    	System.out.println("Score:"+Score);

    	if  (Service == ServiceDelivering.three)
		{Score=Score+3 ;}
    	System.out.println("Score:"+Score);

    	if  (Service == ServiceDelivering.four)
		{Score=Score+4 ;}
    	System.out.println("Score:"+Score);

    	 if  (Service == ServiceDelivering.five)
		{Score=Score+5 ;}
     	System.out.println("Score:"+Score);

    	  if ( Duration ==DurationOfDelivering.absent )
	    {Score=Score-3 ; }
      	System.out.println("Score:"+Score);

    	 if ( Duration ==DurationOfDelivering.late )
	    {Score=Score-1 ; }
     	System.out.println("Score:"+Score);

    	 if ( Duration ==DurationOfDelivering.punctual )
	    {Score=Score+2 ; }
     	System.out.println("Score:"+Score);
     	D.setScoreDelivery(Score);
     	Devr.save(D);
    	return Score; }
         
    // affect deliverer to delivery manually 
     @Transactional
    public void AffectLivreurLivraison(long livreurId, long livraisonId) {

        Delivery De = Devr.findById(livraisonId).get();
        Deliverer Dr = fr.findById(livreurId).get();


        if (ObjectUtils.isEmpty(De)== false && !ObjectUtils.isEmpty(Dr) )
        { De.setDeliverer(Dr);}
        Devr.save(De);
    }
     //return a list of all the delivery scores done by one deliverer
	@Override
	public List<Integer> getScorebyDelivererI(long deliverer_id) {
		//Delivery d = Devr.findById(deliverer_id).get();
		List<Integer> score = Devr.getScorebyDelivererI(deliverer_id);
		return score;
	}
	
	//calculate the sum of all his scores 
	public static int sumScores(List<Integer> list) {
	    int sum = 0;
	    for (int i: list) {
	        sum += i;
	    }
	    return sum;
	}
 	 
    //calculate the delivere's score and set it to his attribute score_deliverer
	public int calculateScoreDeliverer(long deliverer_id) {
		List<Integer> score = Devr.getScorebyDelivererI(deliverer_id);
        Deliverer deliverer = fr.findById(deliverer_id).get();
		int sum_score=0; 
	   sum_score=sumScores(score); 
	   deliverer.setScore_deliverer(sum_score);
       fr.save(deliverer);
		return sum_score;
	}
	

		
// distance using Haversine and set it to the attribute distance
@Override
public double distance(long delvID, long ClientId,long livraisonId) {
	double startLat = fr.findById(delvID).get().getLatitude();
	double startLong = fr.findById(delvID).get().getLongitude();
	double endLat = cl.findById(ClientId).get().getLatitude();
	double endLong = cl.findById(ClientId).get().getLongitude();
	
	
    double dLat = Math.toRadians(endLat - startLat);
    double dLon = Math.toRadians(endLong - startLong);

    // convert to radians
    startLat = Math.toRadians(startLat);
    endLat = Math.toRadians(endLat);

    // apply formulae
    double a = Math.pow(Math.sin(dLat / 2), 2) + 
               Math.pow(Math.sin(dLon / 2), 2) * 
               Math.cos(startLat) * 
               Math.cos(endLat);
    double rad = 6371;
    double c = 2 * Math.asin(Math.sqrt(a));
    double y = rad * c;
    
    Delivery De = Devr.findById(livraisonId).get();
    De.setDistance(y); 
    Devr.save(De);
    return y;
    
}
// To Radiians function
private static Double toRadians(Double value) {
	 return value * Math.PI / 180;
	 }

// calculate the min of a given list
public Double smallestElementList(ArrayList<Double> x){
	Double min_val;
	min_val=x.get(0);
	int n=x.size();
	for (Double i: x){
		if (i<min_val){
	        min_val=i; 
		}
	}
	return min_val;
}

//Affect automatically deliverer to delivery using distance
public void AffectNEARdeliverer(long client_id, long livraisonId) {
	 Delivery De = Devr.findById(livraisonId).get();
	 Client c = cl.findById(client_id).get();

	 List<Deliverer> list = dm.retrieveAllDeliverer();
	 for (Deliverer d: list){ 
		  ArrayList list2 = new ArrayList<Double>();
		  long deliverer_id = d.getIdUser();
		  double x = dr.distance(client_id, deliverer_id,livraisonId); 
		  list2.add(x);
	      Double s = smallestElementList(list2); 
	      int n = list2.indexOf(s);
	 	  Deliverer w = list.get(n);
		  long the_id = w.getIdUser();

     Deliverer Dr = fr.findById(the_id).get(); 
     
     if (ObjectUtils.isEmpty(De)== false && !ObjectUtils.isEmpty(Dr) )
     { De.setDeliverer(Dr);}
     Devr.save(De);
	}
 	}
// delivery frais livraison selon produit and distance
public double FraisLivraison(long order_id){
	 Order r = Or.findById(order_id).get();
     float price_product = r.getCost();
     Delivery d = r.getDelivery();
     float delivery_price=d.getCost_delivery();
     double distance = d.getDistance();
     float new_price= 0.00F;
     if(distance<=50){
    	 new_price=price_product*0.02F;
     }
     else if( (distance<=100) && (50<=distance)){
    	 new_price=price_product*0.05F;
     }
     else if( (distance<=200) && (100<=distance)){
    	 new_price=price_product*0.1F;
     }
     else if( (distance<=500) && (200<=distance)){
    	 new_price=price_product*0.15F;
     }
     else if( (distance<=1000) && (500<=distance)){
    	 new_price=price_product*0.2F;
     }
     else if( (distance<=2000) && (1000<=distance)){
    	 new_price=price_product*0.25F;
     }
     else if( (distance<=3000) && (2000<=distance)){
    	 new_price=price_product*0.3F;
     }
     else if( (distance<=4000) && (3000<=distance)){
    	 new_price=price_product*0.35F;
     }
     else if( (distance<=5000) && (4000<=distance)){
    	 new_price=price_product*0.5F;
     }
     d.setCost_delivery(new_price);
     Devr.save(d);
     return new_price;
}
}