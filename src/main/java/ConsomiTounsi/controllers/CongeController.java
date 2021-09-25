package ConsomiTounsi.controllers;


import ConsomiTounsi.Service.CongeManagerInterface;
import ConsomiTounsi.controllers.simple_controllers.MessageResponseModel;
import ConsomiTounsi.entities.*;
import ConsomiTounsi.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/Conge")
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

    @GetMapping("/GetCongesForChefDep/{userName}")
    public List<Conge> GetCongesForChefDep(@PathVariable("userName") String userName) {
        return CongeI.GetCongesForChefDep(userName);
    }

    @GetMapping("/GetCongesForEmp/{userName}")
    public List<Conge> GetCongesForEmp(@PathVariable("userName") String userName) {
        return CongeI.GetCongesForEmp(userName);
    }


    @PostMapping("/ValidationPrimaireChefDep/{CongeId}/{username}")
    public void ValidationPrimaireChefDep(@PathVariable("CongeId") Long CongeId,@PathVariable("username") String username) {
        CongeI.ValidationPrimaireChefDep(CongeId,username);
    }

    @PostMapping("/ValidationPrimaireRemplaceur/{CongeId}/{username}")
    public void ValidationPrimaireRemplaceur(@PathVariable("CongeId") Long CongeId,@PathVariable("username") String username) {
        CongeI.ValidationPrimaireRemplaceur(CongeId,username);
    }

    @PostMapping("/AnnuleValidationPrimaireChefDep/{CongeId}/{username}/{AvisPrimaire}")
    public void AnnuleValidationPrimaireChefDep(@PathVariable("CongeId") Long CongeId,@PathVariable("username") String username,@PathVariable("AvisPrimaire") String AvisPrimaire) {
        CongeI.AnnuleValidationPrimaireChefDep(CongeId,username,AvisPrimaire);
    }
    @PostMapping("/AnnuleValidationPrimaireRemplaceur/{CongeId}/{username}/{AvisPrimaire}")
    public void AnnuleValidationPrimaireRemplaceur(@PathVariable("CongeId") Long CongeId,@PathVariable("username") String username,@PathVariable("AvisPrimaire") String AvisPrimaire) {
        CongeI.AnnuleValidationPrimaireRemplaceur(CongeId,username,AvisPrimaire);
    }

    @PostMapping("/ValidationFinale/{CongeId}")
    public void ValidationFinale(@PathVariable("CongeId") Long CongeId) {
        CongeI.ValidationFinale(CongeId);
    }
    @PostMapping("/AnnuleValidationFinale/{CongeId}/{AvisFinale}")
    public void AnnuleValidationFinale(@PathVariable("CongeId") Long CongeId,@PathVariable("AvisFinale") String AvisFinale) {
        CongeI.AnnuleValidationFinale(CongeId,AvisFinale);
    }


    @GetMapping("/getiduserbyidconge/{idConge}")
    public DetailsUserConge getDetailsUserByIdConge(@PathVariable("idConge") Long idConge) {
             return CongeI.getDetailsUserByIdConge(idConge);
    }











}
