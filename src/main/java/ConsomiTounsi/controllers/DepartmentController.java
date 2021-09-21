package ConsomiTounsi.controllers;


import ConsomiTounsi.Service.DepartementManager;
import ConsomiTounsi.Service.DepartementManagerInterface;
import ConsomiTounsi.Service.UserManager;
import ConsomiTounsi.Service.UserManagerInterface;
import ConsomiTounsi.controllers.simple_controllers.MessageResponseModel;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.Historique;
import ConsomiTounsi.entities.TypeHistorique;
import ConsomiTounsi.entities.User;
import ConsomiTounsi.repository.ClientRepository;
import ConsomiTounsi.repository.DepartementRepository;
import ConsomiTounsi.repository.HistoriqueRepository;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Department")
@CrossOrigin(origins = "*")
public class DepartmentController {
    @Autowired
    DepartementManagerInterface DepartmentI ;
    @Autowired
    ClientRepository cr ;
    @Autowired
    DepartementRepository dr ;

    @Autowired
    DepartementManager dm ;

    @Autowired
    HistoriqueRepository hr ;

    @Autowired
    UserManagerInterface UMI ;

    @Autowired
    UserManager ur;




    @GetMapping("/getall")
    public List<Departement> retrieveAllDepartment(){
        return DepartmentI.retrieveAllDepartment();
    }


    @GetMapping("/getNameDepartmentByMatriculeBoss/{matricule}")
    public List<String> getNameDepartmentByMatriculeBoss(@PathVariable("matricule") String matricule)
    {
        return dm.getNameDepartmentByMatriculeBoss(matricule);
    }

    @GetMapping("/getNMatriculeByUsernameUser/{username}")
    public String getNMatriculeByUsernameUser(@PathVariable("username") String username)
    {
        return ur.getNMatriculeByUsernameUser(username);
    }

    @PostMapping("/addDep")
    public ResponseEntity addDepartment( @RequestBody Departement department) {


            if (!(cr.existsByMatricule(department.getMatriculeBoss()))) {
                return new ResponseEntity<>(new MessageResponseModel("Matricule boss is not found"),
                        HttpStatus.BAD_REQUEST);
            }

            //Historique
        Historique H = new Historique();

        H.setAction("DEPARTMENT");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        H.setDate(now);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        H.setOwner(username);


        TypeHistorique ex= null;
        H.setTypehistorique(ex.NOT_IMPORTANT);

        //Departement OldD = dr.findById(department.getIdDepartement()).get();
        String Description = "ADD DEPARTMENT => nom de department : "+ department.getNomDepartement()+" // Matricule Boss : "+department.getMatriculeBoss() ;
        H.setDescription(Description);

        hr.save(H);

        /////////////

        DepartmentI.addDepartment(department);
        return new ResponseEntity<>(new MessageResponseModel("Department registered successfully!"), HttpStatus.OK);
    }



    @PutMapping("/update")
    public ResponseEntity<?> updateDepartment(@RequestBody Departement department) {

        if (!(cr.existsByMatricule(department.getMatriculeBoss()))) {
            return new ResponseEntity<>(new MessageResponseModel("Matricule boss is not found"),
                    HttpStatus.BAD_REQUEST);
        }

        // Historique
        Historique H = new Historique();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        H.setDate(now);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        H.setOwner(username);

        H.setAction("DEPARTMENT");

        TypeHistorique ex= null;
        H.setTypehistorique(ex.NOT_IMPORTANT);

        /*Departement OldD = dr.findById(department.getIdDepartement()).get();
        String Description = "UPDATE DEPARTMENT => nom de department : "+ OldD.getNomDepartement() +" => "+ department.getNomDepartement()+" // Matricule Boss : "+ OldD.getMatriculeBoss()+" => "+department.getMatriculeBoss() ;
        H.setDescription(Description);*/

        //test affichage only li tbadel

        Departement OldD = dr.findById(department.getIdDepartement()).get();
        String Description = "UPDATE DEPARTMENT ::" ;

        if(!OldD.getNomDepartement().equals(department.getNomDepartement())){
            Description=Description+" nom de department : "+ OldD.getNomDepartement() +" => "+ department.getNomDepartement()+ " //";
        }
        if (!OldD.getMatriculeBoss().equals(department.getMatriculeBoss())){
            Description=Description+" Matricule Boss : "+ OldD.getMatriculeBoss()+" => "+department.getMatriculeBoss();
        }

        H.setDescription(Description);

        hr.save(H);

        /////////////////

        DepartmentI.updateDepartment(department);
        return new ResponseEntity<>(new MessageResponseModel("Department updated successfully!"),HttpStatus.OK);

    }





    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id) {



        //Historique
        Historique H = new Historique();

        H.setAction("DEPARTMENT");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        H.setDate(now);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        H.setOwner(username);


        TypeHistorique ex= null;
        H.setTypehistorique(ex.IMPORTANT);

        Departement department = dr.findById(id).get();
        String Description = "DELETE DEPARTMENT => nom de department : "+ department.getNomDepartement()+" // Matricule Boss : "+department.getMatriculeBoss() ;
        H.setDescription(Description);

        hr.save(H);

        /////////////





        DepartmentI.deleteDepartmentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
