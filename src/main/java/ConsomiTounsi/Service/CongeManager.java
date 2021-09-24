package ConsomiTounsi.Service;

import ConsomiTounsi.entities.Conge;
import ConsomiTounsi.entities.Departement;
import ConsomiTounsi.entities.User;
import ConsomiTounsi.repository.CongeRepository;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service

public class CongeManager implements CongeManagerInterface {

    @Autowired
    CongeRepository cr ;
    @Autowired
    UserRepository ur ;

    @Override
    public Conge addConge (Conge c){
        return cr.save(c);
    }

    @Override
    public void addCongeEtAffectation (Conge c, String matricule){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        c.setDateSaisie(now);




        cr.save(c);
    }


    @Override
    public void AffectEmployeConge(long CongeId, String matricule) {

        Conge CongeManagedEntity = cr.findById(CongeId).get();

        User clientManagedEntity = ur.findUserByMatricule(matricule).get();


        if (ObjectUtils.isEmpty(CongeManagedEntity)== false && !ObjectUtils.isEmpty(clientManagedEntity) )
        {CongeManagedEntity.setUsers(clientManagedEntity);}

    }

}
