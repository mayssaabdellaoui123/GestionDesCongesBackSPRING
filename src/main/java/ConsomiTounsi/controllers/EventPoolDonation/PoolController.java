package ConsomiTounsi.controllers.EventPoolDonation;


import ConsomiTounsi.Service.PoolManagerInterface;
import ConsomiTounsi.Service.ProductManagerInterface;
import ConsomiTounsi.entities.Pool;
import ConsomiTounsi.entities.Product;
import ConsomiTounsi.entities.Shelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pool")
@CrossOrigin(origins = "*")
public class PoolController {
    @Autowired
    PoolManagerInterface poolmanager ;

    /*{"amount_pool":500}*/



    @PostMapping("/add")
    public void addPool(@RequestBody Pool p){ poolmanager.addPool(p);}

    @GetMapping("/retrieve-all")
    public List<Pool> getListShelves(){ return poolmanager.retrieveAllPool(); }

}
