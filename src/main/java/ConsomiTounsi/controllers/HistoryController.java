package ConsomiTounsi.controllers;

import ConsomiTounsi.Service.DelivererManager;
import ConsomiTounsi.Service.HistoriqueManagerInterface;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.Historique;
import ConsomiTounsi.entities.TypeHistorique;
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


    @GetMapping("/RetiveHistoriqueByType")
    public List<Historique> RetiveHistoriqueByType(TypeHistorique type){
        return Hm.RetiveHistoriqueByType(type);
    }

    @GetMapping("/getNombreHistorique")
    public long getNombreHistorique(){
        return  Hm.getNombreHistorique();
    }



    @GetMapping("/RetiveHistoriqueByOwner")
    public List<Historique> RetiveHistoriqueByOwner( String Username){
        return Hm.RetiveHistoriqueByOwner(Username);
    }

    @GetMapping("/RetiveHistoriqueByAction")
    public List<Historique> RetiveHistoriqueByAction( String action){
        return Hm.RetiveHistoriqueByAction(action);
    }






}
