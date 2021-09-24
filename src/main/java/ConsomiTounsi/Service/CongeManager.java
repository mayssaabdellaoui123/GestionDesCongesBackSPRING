package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Admin;
import ConsomiTounsi.entities.Conge;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.User;
import ConsomiTounsi.repository.DepartementRepository;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CongeManager implements CongeManagerInterface {

    @Autowired
    UserRepository ur;

    @Autowired
    DepartementRepository dr;


    public List<Conge> getCongeChefDepartment(String username){

        Admin a = (Admin) ur.findByUsernameUser(username).get();
        String matricule = a.getMatricule();
        List<Long> departmentname = dr.getIdDepartmentByMatriculeBoss(matricule);



    }
}
