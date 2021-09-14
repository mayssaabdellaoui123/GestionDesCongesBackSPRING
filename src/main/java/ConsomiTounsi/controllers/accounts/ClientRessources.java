package ConsomiTounsi.controllers.accounts;

import ConsomiTounsi.Service.ClientManagerInterface;
import ConsomiTounsi.entities.Admin;
import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.User;
import ConsomiTounsi.entities.UserRole;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ressources/client")
@CrossOrigin(origins = "*")
@RestController
public class ClientRessources {
    @Autowired
    ClientManagerInterface cs;

    @Autowired
    UserRepository ur;

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
        Client updateEmployee = cs.updateClient(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        cs.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getbyusername/{username}")
    public Client getByID(@PathVariable("username") String  username){
        User u = ur.findByUsernameUser(username).orElse(new User());
        return cs.FindClientById(u.getIdUser());}

}
