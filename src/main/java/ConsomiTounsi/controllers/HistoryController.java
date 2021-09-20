package ConsomiTounsi.controllers;

import ConsomiTounsi.Service.DelivererManager;
import ConsomiTounsi.Service.HistoriqueManagerInterface;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.Historique;
import ConsomiTounsi.entities.TypeHistorique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/RetiveHistoriqueByType/{type}")
    public List<Historique> RetiveHistoriqueByType(@PathVariable("type")TypeHistorique type){
        return Hm.RetiveHistoriqueByType(type);
    }

    @GetMapping("/getNombreHistorique")
    public long getNombreHistorique(){
        return  Hm.getNombreHistorique();
    }



    @GetMapping("/RetiveHistoriqueByOwner/{owner}")
    public List<Historique> RetiveHistoriqueByOwner(@PathVariable("owner") String owner){
        return Hm.RetiveHistoriqueByOwner(owner);
    }

    @GetMapping("/RetiveHistoriqueByAction/{action}")
    public List<Historique> RetiveHistoriqueByAction( @PathVariable("action") String action){
        return Hm.RetiveHistoriqueByAction(action);
    }






}
