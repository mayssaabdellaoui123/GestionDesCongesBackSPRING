package ITCEQConge.controllers;


import ITCEQConge.Service.DepartementManager;
import ITCEQConge.Service.DepartementManagerInterface;
import ITCEQConge.Service.UserManager;
import ITCEQConge.Service.UserManagerInterface;
import ITCEQConge.controllers.simple_controllers.MessageResponseModel;
import ITCEQConge.entities.Departement;
import ITCEQConge.repository.EmployeRepository;
import ITCEQConge.repository.DepartementRepository;
import ITCEQConge.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Department")
@CrossOrigin(origins = "*")
public class DepartmentController {
    @Autowired
    DepartementManagerInterface DepartmentI ;
    @Autowired
    EmployeRepository cr ;
    @Autowired
    DepartementRepository dr ;

    @Autowired
    DepartementManager dm ;

    @Autowired
    DepartementManagerInterface dmI ;



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
    public  String getNameDepartmentByMatriculeBoss(@PathVariable("matricule") String matricule)
    {
        return dmI.getNameDepartmentByMatriculeBoss(matricule);
    }

   /* @GetMapping("/getNMatriculeByUsernameUser/{username}")
    public String getNMatriculeByUsernameUser(@PathVariable("username") String username)
    {
        return ur.getNMatriculeByUsernameUser(username);
    }*/

    @PostMapping("/addDep")
    public ResponseEntity addDepartment( @RequestBody Departement department) {

           /* if (!(cr.existsByMatricule(department.getMatriculeBoss()))) {
                return new ResponseEntity<>(new MessageResponseModel("Matricule boss is not found"),
                        HttpStatus.BAD_REQUEST);
            }*/

        DepartmentI.addDepartment(department);
        return new ResponseEntity<>(new MessageResponseModel("Department registered successfully!"), HttpStatus.OK);
    }



    @PutMapping("/update")
    public ResponseEntity<?> updateDepartment(@RequestBody Departement department) {

        if ((dr.existsByMatriculeBoss(department.getMatriculeBoss()))) {
            return new ResponseEntity<>(new MessageResponseModel("Matricule boss is taken"),
                    HttpStatus.BAD_REQUEST);
        }

       if (!(cr.existsByMatricule(department.getMatriculeBoss()))) {
            return new ResponseEntity<>(new MessageResponseModel("User not found"),
                    HttpStatus.BAD_REQUEST);
        }

        // Historique
       /* Historique H = new Historique();

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

       /* Departement OldD = dr.findById(department.getIdDepartement()).get();
        String Description = "UPDATE DEPARTMENT ::" ;

        if(!OldD.getNomDepartement().equals(department.getNomDepartement())){
            Description=Description+" nom de department : "+ OldD.getNomDepartement() +" => "+ department.getNomDepartement()+ " //";
        }
        if (!OldD.getMatriculeBoss().equals(department.getMatriculeBoss())){
            Description=Description+" Matricule Boss : "+ OldD.getMatriculeBoss()+" => "+department.getMatriculeBoss();
        }

        H.setDescription(Description);

        hr.save(H);*/

        /////////////////

        DepartmentI.updateDepartment(department);
        return new ResponseEntity<>(new MessageResponseModel("Department updated successfully!"),HttpStatus.OK);

    }





    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id) {



        //Historique
        /*Historique H = new Historique();

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
*/
        /////////////

        DepartmentI.deleteDepartmentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping("/AffectEmployeeDepartment/{DepartId}/{matricule}")
    public void AffectEmployeeDepartment(@PathVariable("DepartId") long DepartId, @PathVariable("matricule") String matricule) {
        dm.AffectEmployeeDepartment(DepartId, matricule);
    }



    @DeleteMapping("/DesaffectEmployeeDepartment/{DepartId}/{matricule}")
    public void DesaffectEmployeeDepartment(@PathVariable("DepartId") long DepartId, @PathVariable("matricule") String matricule) {
        dm.DesaffectEmployeeDepartment(DepartId, matricule);
    }


    /*************************/


    @GetMapping("/retrieveNameDepartmentByUsername/{username}")
    public  Departement retrieveNameDepartmentByUsername(@PathVariable("username") String username)
    {
        return DepartmentI.retrieveNameDepartmentByUsername(username);
    }

    @GetMapping("/retrieveNameDepartmentByUsernameChef/{username}")
    public  Departement retrieveNameDepartmentByUsernameChef(@PathVariable("username") String username)
    {
        return DepartmentI.retrieveNameDepartmentByUsernameChef(username);
    }

}
