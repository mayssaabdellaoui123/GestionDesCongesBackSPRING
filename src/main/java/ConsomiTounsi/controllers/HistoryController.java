package ConsomiTounsi.controllers;

import ConsomiTounsi.Service.DelivererManager;
import ConsomiTounsi.Service.HistoriqueManagerInterface;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.Historique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Historique")
public class HistoryController {

    @Autowired
    HistoriqueManagerInterface Hm;

    @GetMapping("/getall")
    public List<Historique> getAllHistory(){
        return Hm.retrieveAllHistory();
    }



}
