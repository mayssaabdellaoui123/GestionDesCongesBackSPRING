package ConsomiTounsi.Service;

import ConsomiTounsi.entities.User;
import ConsomiTounsi.repository.DepartementRepository;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CongeManager implements CongeManagerInterface {

    @Autowired
    UserRepository ur;

    @Autowired
    DepartementRepository dr;


    public List<User> getCongeChefDepartment(String username){

        User a = ur.findByUsernameUser(username).get();
        String matricule = a.getMatricule();
        System.out.println(matricule);
        long departmentids = dr.getIdDepartmentByMatriculeBoss(matricule);
        System.out.println(departmentids);

        List<User> u = ur.retrieveUsersByDepartment(departmentids);


        return u;

    }
}
