package ConsomiTounsi.controllers;


import ConsomiTounsi.Service.DepartementManagerInterface;
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
    HistoriqueRepository hr ;

    @Autowired
    UserManagerInterface UMI ;




    @GetMapping("/getall")
    public List<Departement> retrieveAllDepartment(){
        return DepartmentI.retrieveAllDepartment();
    }

    @PostMapping("/addDep")
    public ResponseEntity addDeliverer( @RequestBody Departement D) {


            if (!(cr.existsByMatricule(D.getMatriculeBoss()))) {
                return new ResponseEntity<>(new MessageResponseModel("Matricule boss is not found"),
                        HttpStatus.BAD_REQUEST);
            }


        DepartmentI.addDepartment(D);
        return new ResponseEntity<>(new MessageResponseModel("Department registered successfully!"), HttpStatus.OK);
    }

    /*@PutMapping("/update")
    public ResponseEntity<Departement> updateDepartment(@RequestBody Departement department) {
        Departement updateDepartment = DepartmentI.updateDepartment(department);
        return new ResponseEntity<>(updateDepartment, HttpStatus.OK);
    }*/

    @PutMapping("/update")
    public ResponseEntity<?> updateDepartment(@RequestBody Departement department) {

        if (!(cr.existsByMatricule(department.getMatriculeBoss()))) {
            return new ResponseEntity<>(new MessageResponseModel("Matricule boss is not found"),
                    HttpStatus.BAD_REQUEST);
        }

        Historique H = new Historique();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        H.setDate(now);



        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        H.setOwner(username);

        H.setAction("DEPARTMENT");

        TypeHistorique ex= null;
        H.setType(ex.NOT_IMPORTANT);


        Departement OldD = dr.findById(department.getIdDepartement()).get();


        String Description = "UPDATE DEPARTMENT => nom de department : "+ OldD.getNomDepartement() +" => "+ department.getNomDepartement()+" // Matricule Boss : "+ OldD.getMatriculeBoss()+" => "+department.getMatriculeBoss() ;
        H.setDescription(Description);


        hr.save(H);

        DepartmentI.updateDepartment(department);
        return new ResponseEntity<>(new MessageResponseModel("Department updated successfully!"),HttpStatus.OK);

    }





    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id) {
        DepartmentI.deleteDepartmentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
