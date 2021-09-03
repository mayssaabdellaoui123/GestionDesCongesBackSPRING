package ConsomiTounsi.controllers;


import ConsomiTounsi.Service.DepartementManagerInterface;
import ConsomiTounsi.controllers.mouadh_controllers.MessageResponseModel;
import ConsomiTounsi.entities.Client;
import ConsomiTounsi.entities.Deliverer;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
}
