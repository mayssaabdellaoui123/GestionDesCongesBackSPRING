package ConsomiTounsi.controllers;


import ConsomiTounsi.Service.CongeManagerInterface;
import ConsomiTounsi.controllers.simple_controllers.MessageResponseModel;
import ConsomiTounsi.entities.Conge;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.Historique;
import ConsomiTounsi.entities.TypeHistorique;
import ConsomiTounsi.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/Conge")
@CrossOrigin(origins = "*")
public class CongeController {

    @Autowired
    CongeManagerInterface CongeI ;

    @Autowired
    CongeRepository cr ;



        @PostMapping("/addConge")
    public ResponseEntity addConge(@RequestBody Conge conge) {

        CongeI.addConge(conge);
        return new ResponseEntity<>(new MessageResponseModel("Conge registered successfully!"), HttpStatus.OK);

    }

    @PostMapping("/AffectEmployeConge/{idConge}/{matricule}")
    public ResponseEntity AffectEmployeConge(@PathVariable("idConge") Long idConge,@PathVariable("matricule") String matricule) {

        CongeI.AffectEmployeConge(idConge,matricule);
        return new ResponseEntity<>(new MessageResponseModel("Conge registered successfully!"), HttpStatus.OK);

    }



    @PostMapping("/addCongeEtAffectation/{userName}")
    public void addCongeEtAffectation(@RequestBody Conge conge,@PathVariable("userName") String userName) {

         CongeI.addCongeEtAffectation(conge,userName);

    }
}
