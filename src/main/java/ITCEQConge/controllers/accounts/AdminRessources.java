package ITCEQConge.controllers.accounts;

import ITCEQConge.Service.AdminManagerInterface;
import ITCEQConge.controllers.simple_controllers.MessageResponseModel;
import ITCEQConge.entities.Admin;
import ITCEQConge.entities.Role;
import ITCEQConge.entities.User;
import ITCEQConge.repository.DepartementRepository;
import ITCEQConge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ressources/admin")
@CrossOrigin(origins = "*")
@RestController
public class AdminRessources {
    @Autowired
    AdminManagerInterface cs;

    @Autowired
    UserRepository ur;
    @Autowired
    DepartementRepository Dr;

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllEmployees () {
        List<Admin> employees = cs.retrieveAllAdmin();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //ajout ADMIN not EMPLOYEE
    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody Admin employee) {

       /* if (!(Dr.existsByMatriculeBoss(employee.getMatriculeBoss()))) {
            return new ResponseEntity<>(new MessageResponseModel("Matricule boss is not found"),
                    HttpStatus.BAD_REQUEST);
        }*/

        if (employee.getRoleAdmin()== Role.DEPARTMENT_BOSS){
            if (!(Dr.existsByMatriculeBoss(employee.getMatriculeBoss()))) {
                return new ResponseEntity<>(new MessageResponseModel("Matricule boss is not found"),
                        HttpStatus.BAD_REQUEST);
            }
        }

        Admin newEmployee = cs.AddAdmin(employee);
        return new ResponseEntity<>(new MessageResponseModel("Admin registered successfully!"), HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Admin> getEmployeeById (@PathVariable("id") Long id) {
        Admin employee = cs.FindAdminById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> updateEmployee(@RequestBody Admin employee) {
        Admin updateEmployee = cs.updateAdmin(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        cs.deleteAdminById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/absent/{id}")
    public void addAbsence(@PathVariable("id") Long  id){cs.addAbsence(id);}

    @GetMapping("getbyid/{id}")
    public Admin getByID(@PathVariable("id") Long  id){return cs.FindAdminById(id);}

    @GetMapping("/getbyusername/{username}")
    public Admin getByID(@PathVariable("username") String  username){
        User u = ur.findByUsernameUser(username).orElse(new User());
        return cs.FindAdminById(u.getIdUser());}
}
