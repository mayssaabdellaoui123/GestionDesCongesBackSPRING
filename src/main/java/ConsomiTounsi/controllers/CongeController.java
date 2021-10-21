package ConsomiTounsi.controllers;


import ConsomiTounsi.Service.ClientManager;
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

    @Autowired
    ClientManager cm;




    @PostMapping("/addConge")

    public ResponseEntity addConge(@RequestBody Conge conge) {

        CongeI.addConge(conge);
        return new ResponseEntity<>(new MessageResponseModel("Conge registered successfully!"), HttpStatus.OK);

    }

    @DeleteMapping("/deleteConge/{idConge}")
    public ResponseEntity deleteConge(@PathVariable("idConge") Long idConge) {
        if(CongeI.deleteConge(idConge)){
            return new ResponseEntity<>(new MessageResponseModel("Conge deleted successfully!"), HttpStatus.OK);
        }
        else return new ResponseEntity<>(new MessageResponseModel("Conge CANNOT be deleted!"), HttpStatus.OK);
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

    @GetMapping("/GetCongesForSA/")
    public List<Conge> GetCongesForSA() {
        return CongeI.GetCongesForSA();
    }

    @GetMapping("/GetCongesForDirecGen/")
    public List<Conge> GetCongesForDirecGen() {
        return CongeI.GetCongesForDirecGen();
    }

    @DeleteMapping("/ValidationPrimaireChefDep/{CongeId}/{username}")
    public void ValidationPrimaireChefDep(@PathVariable("CongeId") Long CongeId,@PathVariable("username") String username) {
        CongeI.ValidationPrimaireChefDep(CongeId,username);
    }

    @DeleteMapping("/ValidationPrimaireRemplaceur/{CongeId}/{username}")
    public void ValidationPrimaireRemplaceur(@PathVariable("CongeId") Long CongeId,@PathVariable("username") String username) {
        CongeI.ValidationPrimaireRemplaceur(CongeId,username);
    }

    @DeleteMapping("/AnnuleValidationPrimaireChefDep/{CongeId}/{username}/{AvisPrimaire}")
    public void AnnuleValidationPrimaireChefDep(@PathVariable("CongeId") Long CongeId,@PathVariable("username") String username,@PathVariable("AvisPrimaire") String AvisPrimaire) {
        CongeI.AnnuleValidationPrimaireChefDep(CongeId,username,AvisPrimaire);
    }
    @DeleteMapping("/AnnuleValidationPrimaireRemplaceur/{CongeId}/{username}/{AvisPrimaire}")
    public void AnnuleValidationPrimaireRemplaceur(@PathVariable("CongeId") Long CongeId,@PathVariable("username") String username,@PathVariable("AvisPrimaire") String AvisPrimaire) {
        CongeI.AnnuleValidationPrimaireRemplaceur(CongeId,username,AvisPrimaire);
    }

    @DeleteMapping("/ValidationFinale/{userNameVF}/{CongeId}/{soldeThisYear}/{soldeYear_1}/{soldeYear_2}/{total}/{nmbrDeJourConge}/{RestDeJourConge}/{AvisFinale}/{TypeValidation}")
    public void ValidationFinale(@PathVariable("userNameVF") String userNameVF ,@PathVariable("CongeId") Long CongeId , @PathVariable("soldeThisYear") String soldeThisYear ,@PathVariable("soldeYear_1") String soldeYear_1, @PathVariable("soldeYear_2") String soldeYear_2 ,@PathVariable("total") String total , @PathVariable("nmbrDeJourConge") String nmbrDeJourConge , @PathVariable("RestDeJourConge") String RestDeJourConge ,@PathVariable("AvisFinale") String AvisFinale,@PathVariable("TypeValidation") Boolean TypeValidation ) {
        CongeI.ValidationFinale(userNameVF,CongeId,soldeThisYear,soldeYear_1,soldeYear_2,total,nmbrDeJourConge,RestDeJourConge,AvisFinale,TypeValidation );
    }
   /* @DeleteMapping("/AnnuleValidationFinale/{CongeId}/{AvisFinale}")
    public void AnnuleValidationFinale(@PathVariable("CongeId") Long CongeId,@PathVariable("AvisFinale") String AvisFinale) {
        CongeI.AnnuleValidationFinale(CongeId,AvisFinale);
    }
*/

    @GetMapping("/getDetailsUserByIdConge/{idConge}")
    public DetailsUserConge getDetailsUserByIdConge(@PathVariable("idConge") Long idConge) {
             return CongeI.getDetailsUserByIdConge(idConge);
    }


    @GetMapping("/getCongeByIdConge/{idConge}")
    public Conge getCongeByIdConge(@PathVariable("idConge") Long idConge) {
        return CongeI.getCongeByIdConge(idConge);
    }

    @GetMapping("/getusernameUserByMatricule/{matricule}")
    public Client getusernameUserByMatricule(@PathVariable("matricule") String matricule){
        return  CongeI.getusernameUserByMatricule(matricule);
    }


    @GetMapping("/getusernameUserByMatriculeForDirecteur/{matricule}")
    public Admin getusernameUserByMatriculeForDirecteur(@PathVariable("matricule") String matricule){
        return  CongeI.getusernameUserByMatriculeForDirecteur(matricule);
    }


    @GetMapping("/findRemplaceurByUserName/{username}")
    public Boolean findRemplaceurByUserName(@PathVariable("username") String username){
        return  CongeI.findRemplaceurByUserName(username);
    }


    @GetMapping("/GetCongesForRemplaceur/{username}")
    public List<Conge> GetCongesForRemplaceur(@PathVariable("username") String username){
        return  CongeI.GetCongesForRemplaceur(username);
    }


    @GetMapping("/retriveRemplaceur/{username}")
    public List<User> retriveRemplaceur(@PathVariable("username")  String username ){
        return cm.retriveRemplaceur(username);
    }










}
