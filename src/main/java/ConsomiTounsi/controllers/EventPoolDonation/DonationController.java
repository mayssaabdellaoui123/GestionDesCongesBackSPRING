package ConsomiTounsi.controllers.EventPoolDonation;


import ConsomiTounsi.Service.DonationManager;
import ConsomiTounsi.entities.Donation;
import ConsomiTounsi.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Donation")
@CrossOrigin(origins = "*")

public class DonationController {

    @Autowired
    DonationManager manager;

    /*@PutMapping("/AddDonaton/{idevent}/{idorder}")
    public void addDonation(@RequestBody Donation d, @PathVariable("idevent") long idevent , @PathVariable("idorder") long idorder){
        manager.addDonation(d,idevent,idorder);
    }*/

    @GetMapping("/retrieve-all")
    public List<Donation> getListEvents(){ return manager.retrieveAllDonation(); }
}
