/*package ConsomiTounsi.Service;

import ConsomiTounsi.entities.User;
import ConsomiTounsi.entities.Conge;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.User;
import ConsomiTounsi.repository.CongeRepository;
import ConsomiTounsi.repository.DepartementRepository;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

    CongeRepository cr ;
    @Autowired
    UserRepository ur ;

    @Autowired
    DepartementRepository dr ;

    @Autowired
    CongeManagerInterface CongeI ;

    @Override// ajout conge avec date saisie
    public Conge addConge (Conge c){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        c.setDateSaisie(now);

        return cr.save(c);
    }

   @Override
    public void addCongeEtAffectation (Conge c, String username){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        c.setDateSaisie(now);

        String matricule = ur.findByUsernameUser(username).get().getMatricule();

        CongeI.AffectEmployeConge(cr.save(c).getIdConge(),matricule) ;

    }




    @Transactional
    public void AffectEmployeConge(long CongeId, String matricule) {
        Conge CongeManagedEntity = cr.findById(CongeId).get();
        User clientManagedEntity = ur.findUserByMatricule(matricule).get();

        if (ObjectUtils.isEmpty(CongeManagedEntity)== false && !ObjectUtils.isEmpty(clientManagedEntity) )
        {CongeManagedEntity.setUsers(clientManagedEntity);}


    }

    @Override
    public List<Conge> getiddep (String username){
        User u = ur.findByUsernameUser(username).get();
        List<Long> IdDep = dr.getIdDepartmentByMatriculeBoss(u.getMatricule());

        List<User> usersDep = new ArrayList<>();

        for(Long id: IdDep){
            usersDep.addAll(ur.getUserByIdDep(id));
        }

        List<Conge> conges = new ArrayList<>();
        for(User userDep:usersDep){
            conges.addAll(cr.getCongeByUserIdUserAndVF(userDep.getIdUser(),Boolean.FALSE));
        }
        return conges ;
    }
}*/
