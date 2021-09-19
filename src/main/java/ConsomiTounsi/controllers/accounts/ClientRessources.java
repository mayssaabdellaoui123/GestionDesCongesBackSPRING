package ConsomiTounsi.controllers.accounts;

import ConsomiTounsi.Service.ClientManagerInterface;
import ConsomiTounsi.entities.*;
import ConsomiTounsi.repository.AdminRepository;
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

@RequestMapping("/ressources/client")
@CrossOrigin(origins = "*")
@RestController
public class ClientRessources {
    @Autowired
    ClientManagerInterface cs;

    @Autowired
    UserRepository ur;

    @Autowired
    AdminRepository ar;

    @Autowired
    HistoriqueRepository hr ;

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllEmployees () {
        List<Client> employees = cs.retrieveAllClient();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/allclients")
    public ResponseEntity<List<User>> getAllClient () {
        List<User> employees = ur.findbyRole(UserRole.CLIENT);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addEmployee(@RequestBody Client employee) {
        Client newEmployee = cs.addClient(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Client> getEmployeeById (@PathVariable("id") Long id) {
        Client employee = cs.FindClientById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateEmployee(@RequestBody Client employee) {

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
        H.setType(ex.NOT_IMPORTANT);


        /*User OldE = ur.findById(employee.getIdUser()).get();
        String Description = "UPDATE EMPLOYEE => User Name  : "+ OldE.getUsernameUser() +" => "+ employee.getUsernameUser()+" // Matricule : "+ OldE.getMatricule()+" => "+employee.getMatricule()+" // First Name : "+ OldE.getFirstNameUser()+" => "+employee.getFirstNameUser()+" // Last Name : "+ OldE.getMatricule()+" => "+employee.getMatricule() ;
        H.setDescription(Description);*/

        User OldE = ur.findById(employee.getIdUser()).get();
        String Description = "UPDATE EMPLOYEE ::" ;

        if(!OldE.getUsernameUser().equals(employee.getUsernameUser())){
            Description=Description+" User Name : "+ OldE.getUsernameUser() +" => "+ employee.getUsernameUser()+ " //";
        }
        if (!OldE.getMatricule().equals(employee.getMatricule())){
            Description=Description+" Matricule : "+ OldE.getMatricule()+" => "+employee.getMatricule()+ " //";
        }



        hr.save(H);
        /////////////

        Client updateEmployee = cs.updateClient(employee);
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
        H.setType(ex.NOT_IMPORTANT);

        User employee = ur.findById(id).get();
        String Description = "DELETE EMPLOYEE => User Name : "+ employee.getUsernameUser()+" // Matricule : "+employee.getMatricule() ;
        H.setDescription(Description);

        hr.save(H);
        /////////////

        cs.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getbyusername/{username}")
    public Client getByID(@PathVariable("username") String  username){
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






}
