package ConsomiTounsi.controllers.accounts;

import ConsomiTounsi.Service.DelivererManagerInterface;
import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Deliverer;
import ConsomiTounsi.entities.User;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ressources/deliverer")
@CrossOrigin(origins = "*")
@RestController
public class DelivererRessources {

    @Autowired
    DelivererManagerInterface cs;

    @Autowired
    UserRepository ur;

    @GetMapping("/all")
    public ResponseEntity<List<Deliverer>> getAllEmployees () {
        List<Deliverer> employees = cs.retrieveAllDeliverer();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Deliverer> getEmployeeById (@PathVariable("id") Long id) {
        Deliverer employee = cs.FindDelivererById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Deliverer> updateEmployee(@RequestBody Deliverer employee) {
        Deliverer updateEmployee = cs.updateDeliverer(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Deliverer> addEmployee(@RequestBody Deliverer employee) {
        Deliverer newEmployee = cs.addDeliverer(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        cs.deleteDelivererById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getbyusername/{username}")
    public Deliverer getByID(@PathVariable("username") String  username){
        User u = ur.findByUsernameUser(username).orElse(new User());
        return cs.FindDelivererById(u.getIdUser());}

}
