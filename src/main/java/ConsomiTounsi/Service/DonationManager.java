package ConsomiTounsi.Service;

import ConsomiTounsi.entities.*;
import ConsomiTounsi.repository.DonationRepository;

import java.util.List;
import java.util.Optional;

import ConsomiTounsi.repository.EventRepository;
import ConsomiTounsi.repository.OrderRepository;
import ConsomiTounsi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service 
public class DonationManager implements DonationManagerInterface{
	
	 @Autowired
	    DonationRepository Dor;
    @Autowired
    ProductRepository Pr;
    @Autowired
    EventRepository Ev;
    @Autowired
    OrderRepository  Or;



    @Override
    public List<Donation> retrieveAllDonation() {
        return  (List<Donation>) Dor.findAll();
    }

   /* @Override
    public void addDonation(Donation Do,long idEvent,long idOrder) {
        Dor.save(Do);
        Event eventManagedEntity = Ev.findById(idEvent).get();
        if (ObjectUtils.isEmpty(eventManagedEntity)== false && !ObjectUtils.isEmpty(Do) )
        {Do.setEvent(eventManagedEntity);}
        Product c = Pr.findById(Do.getProduct_donation()).get();
         Order o= Or.findById(idOrder).get();
        c.addorders(o);//pour ajouter l'employé a une mission (l'ajout se fait dans la table "Employees_Missions")
        o.addproduct(c);
        Or.save(o);
        Pr.save(c);*/

   // }

    @Override
    public void deleteDonation(Long id) {
        Dor.deleteById(id);
    }

    @Override
    public void deleteDonation(String id) {
        Dor.deleteById(Long.parseLong(id));
    }

    @Override
    public Donation updateDonation(Donation Do) {
        return Dor.save(Do) ;
    }

    @Override
    public Donation FindDonationById(Long id) {
        return Dor.findById(id).orElse(new Donation());
    }

    @Override
    public Donation FindDonationById(String id) {
        return Dor.findById(Long.parseLong(id)).orElse(new Donation());
    }



}
