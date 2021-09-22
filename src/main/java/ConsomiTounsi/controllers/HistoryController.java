package ConsomiTounsi.controllers;

import ConsomiTounsi.Service.DelivererManager;
import ConsomiTounsi.Service.HistoriqueManagerInterface;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.Historique;
import ConsomiTounsi.entities.TypeHistorique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Historique")
public class HistoryController {

    @Autowired
    HistoriqueManagerInterface Hm;

    @GetMapping("/getall")
    public List<Historique> getAllHistory(){
        List<Historique> lh = Hm.retrieveAllHistory() ;
        Collections.reverse(lh);
        return lh;
    }


    @GetMapping("/RetiveHistoriqueByType/{type}")
    public List<Historique> RetiveHistoriqueByType(@PathVariable("type")TypeHistorique type){
        List<Historique> lh =Hm.RetiveHistoriqueByType(type);
        Collections.reverse(lh);
        return lh;
    }

    @GetMapping("/getNombreHistorique")
    public long getNombreHistorique(){
        return  Hm.getNombreHistorique();
    }



    @GetMapping("/RetiveHistoriqueByOwner/{owner}")
    public List<Historique> RetiveHistoriqueByOwner(@PathVariable("owner") String owner){
        List<Historique> lh =Hm.RetiveHistoriqueByOwner(owner);
        Collections.reverse(lh);
        return lh;
    }

    @GetMapping("/RetiveHistoriqueByAction/{action}")
    public List<Historique> RetiveHistoriqueByAction( @PathVariable("action") String action){
        List<Historique> lh =Hm.RetiveHistoriqueByAction(action);
        Collections.reverse(lh);
        return lh ;
    }

    @GetMapping("/RetiveHistoriqueByFiltre/{type}/{action}")
    public List<Historique> RetiveHistoriqueByFiltre( @PathVariable("type")TypeHistorique type,@PathVariable("action") String action){
        List<Historique> lh =Hm.RetiveHistoriqueByFiltre(type,action);
        Collections.reverse(lh);
        return lh;
    }






}
