package ITCEQConge.controllers.accounts;

import ITCEQConge.Service.EmployeManager;
import ITCEQConge.Service.EmployeManagerInterface;
import ITCEQConge.Service.DepartementManager;
import ITCEQConge.entities.*;
import ITCEQConge.repository.AdminRepository;
import ITCEQConge.repository.EmployeRepository;
import ITCEQConge.repository.HistoriqueRepository;
import ITCEQConge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@RequestMapping("/ressources/client")
@CrossOrigin(origins = "*")
@RestController
public class EmployeRessources {
    @Autowired
    EmployeManagerInterface cs;

    @Autowired
    UserRepository ur;

    @Autowired
    DepartementManager dm;

    @Autowired
    EmployeRepository cr;
    @Autowired
    AdminRepository ar;

    @Autowired
    HistoriqueRepository hr ;

    @Autowired
    EmployeManager cm;


    @GetMapping("/all")
    public ResponseEntity<List<Employe>> getAllEmployees () {
        List<Employe> employees = cs.retrieveAllClient();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/allclients")
    public ResponseEntity<List<User>> getAllClient () {
        List<User> employees = ur.findbyRole(UserRole.CLIENT);
        Collections.reverse(employees);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employe> addEmployee(@RequestBody Employe employee) {


        //Historique
        Historique H = new Historique();

        H.setAction("EMPLOYEE");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        H.setDate(now);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        H.setOwner(username);


        TypeHistorique ex= null;
        H.setTypehistorique(ex.NOT_IMPORTANT);


        String Description = "ADD EMPLOYEE => User Name : "+ employee.getUsernameUser()+" // Matricule : "+employee.getMatricule()+" // First Name : "+employee.getFirstNameUser()+" // Last Name : "+employee.getLastNameUser()+" // Email : "+employee.getEmailAddressUser()+" // Date Birth : "+employee.getDateBirthUser()+" // Phone : "+employee.getPhoneNumberUser()+" // Gender : "+employee.getGenderClient()+" // Workfield : "+employee.getWorkfieldClient() ;
        H.setDescription(Description);

        hr.save(H);
        /////////////


        Employe newEmployee = cs.addClient(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employe> getEmployeeById (@PathVariable("id") Long id) {
        Employe employee = cs.FindClientById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Employe> updateEmployee(@RequestBody Employe employee) {

        ////

        User u = cr.findById(employee.getIdUser()).get();

        Departement d = u.getDepartement();




        ////

        //Historique
        Historique H = new Historique();

        H.setAction("EMPLOYEE");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        H.setDate(now);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        H.setOwner(username);


        TypeHistorique ex= null;
        H.setTypehistorique(ex.NOT_IMPORTANT);


        /*User OldE = ur.findById(employee.getIdUser()).get();
        String Description = "UPDATE EMPLOYEE => User Name  : "+ OldE.getUsernameUser() +" => "+ employee.getUsernameUser()+" // Matricule : "+ OldE.getMatricule()+" => "+employee.getMatricule()+" // First Name : "+ OldE.getFirstNameUser()+" => "+employee.getFirstNameUser()+" // Last Name : "+ OldE.getMatricule()+" => "+employee.getMatricule() ;
        H.setDescription(Description);*/

        Employe OldE = cr.findById(employee.getIdUser()).get();
        String Description = "UPDATE EMPLOYEE ::" ;

        if (!OldE.getMatricule().equals(employee.getMatricule())){
            Description=Description+" Matricule : "+ OldE.getMatricule()+" => "+employee.getMatricule()+ " //";
        }
        /*if(!OldE.getSoldeDeConge().equals(employee.getSoldeDeConge())){
            Description=Description+" Solde De Conge : "+ OldE.getSoldeDeConge() +" => "+ employee.getSoldeDeConge()+ " //";
        }*/
        if(!OldE.getUsernameUser().equals(employee.getUsernameUser())){
            Description=Description+" User Name : "+ OldE.getUsernameUser() +" => "+ employee.getUsernameUser()+ " //";
        }
        if(!OldE.getFirstNameUser().equals(employee.getFirstNameUser())){
            Description=Description+" First Name : "+ OldE.getFirstNameUser() +" => "+ employee.getFirstNameUser()+ " //";
        }
        if(!OldE.getLastNameUser().equals(employee.getLastNameUser())){
            Description=Description+" Last Name : "+ OldE.getLastNameUser() +" => "+ employee.getLastNameUser()+ " //";
        }
        if(!OldE.getEmailAddressUser().equals(employee.getEmailAddressUser())){
            Description=Description+" Email : "+ OldE.getEmailAddressUser() +" => "+ employee.getEmailAddressUser()+ " //";
        }
        if(!OldE.getDateBirthUser().equals(employee.getDateBirthUser())){
            Description=Description+" Date Birth : "+ OldE.getDateBirthUser() +" => "+ employee.getDateBirthUser()+ " //";
        }
        if(!OldE.getPhoneNumberUser().equals(employee.getPhoneNumberUser())){
            Description=Description+" Phone : "+ OldE.getPhoneNumberUser() +" => "+ employee.getPhoneNumberUser()+ " //";
        }
        /*if(!OldE.getDepartement().equals(employee.getDepartement())){
            Description=Description+" Department : "+ OldE.getDepartement() +" => "+ employee.getDepartement()+ " //";
        }*/
        if(!OldE.getGenderClient().equals(employee.getGenderClient())){
            Description=Description+" Gender : "+ OldE.getGenderClient() +" => "+ employee.getGenderClient()+ " //";
        }
        if(!OldE.getWorkfieldClient().equals(employee.getWorkfieldClient())){
            Description=Description+" Workfield : "+ OldE.getWorkfieldClient() +" => "+ employee.getWorkfieldClient()+ " //";
        }

        H.setDescription(Description);



        hr.save(H);
        /////////////

        Employe updateEmployee = cs.updateClient(employee);

        dm.AffectEmployeeDepartment(d.getIdDepartement(),updateEmployee.getMatricule());
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {

        //Historique
        Historique H = new Historique();

        H.setAction("EMPLOYEE");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        H.setDate(now);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        H.setOwner(username);


        TypeHistorique ex= null;
        H.setTypehistorique(ex.NOT_IMPORTANT);

        User employee = ur.findById(id).get();
        String Description = "DELETE EMPLOYEE => User Name : "+ employee.getUsernameUser()+" // Matricule : "+employee.getMatricule() ;
        H.setDescription(Description);

        hr.save(H);
        /////////////

        cs.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getbyusername/{username}")
    public Employe getByID(@PathVariable("username") String  username){
        User u = ur.findByUsernameUser(username).orElse(new User());
        return cs.FindClientById(u.getIdUser());}

    @GetMapping("/getRolebyusername/{username}")
    public String getRoleByusername(@PathVariable("username") String  username){
        //User u = ur.findByUsernameUser(username);
        //Role r = u.getr;

        Long idu = ur.findByUsernameUser(username).get().getIdUser();
        System.out.println(idu);
        String r = ar.findRoleById(idu).toString();

        return r;}

    @GetMapping("/getRolebyusernameid/{id}")
    public String getRoleByusernameid(@PathVariable("id") Long  id){
        //User u = ur.findByUsernameUser(username);
        //Role r = u.getr;

        String r = ar.findRoleById(id).toString();

        return r;}



    @DeleteMapping("/AffectationRemplaceur/{matricule}")
    public Employe AffectationRemplaceur(@PathVariable("matricule") String  matricule) {
        //User u = ur.findByUsernameUser(username);
        //Role r = u.getr;



        return cs.AffectationRemplaceur(matricule);
    }

    @DeleteMapping("/DesAffectationRemplaceur/{matricule}")
    public Employe DesAffectationRemplaceur(@PathVariable("matricule") String  matricule) {
        //User u = ur.findByUsernameUser(username);
        //Role r = u.getr;



        return cs.DesAffectationRemplaceur(matricule);
    }





    @GetMapping("/getClientByMAtricule/{matricule}")
    public Employe getClientByMAtricule(@PathVariable("matricule") String matricule  ){
        return cr.findClientByMatricule(matricule);}

    @GetMapping("/getUserNameFromMatricule/{matricule}")
    public String getUserNameFromMatricule(@PathVariable("matricule") String matricule  ){
        return cs.getUserNameFromMatricule(matricule);
    }


    @GetMapping("/EmployeeByDep/{username}")
    public List<User> getEmployeeByDepartment (@PathVariable("username") String username)
    {
        return cm.getEmployeeByDepartment(username);
    }




}